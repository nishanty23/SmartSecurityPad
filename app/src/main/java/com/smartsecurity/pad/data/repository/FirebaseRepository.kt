package com.smartsecurity.pad.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.smartsecurity.pad.data.model.DeviceData
import com.smartsecurity.pad.data.model.HistoryItem

class FirebaseRepository {

    private val database = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun listenToPad(
        deviceId: String = "PAD_001",
        onData: (DeviceData) -> Unit,
        onError: (String) -> Unit
    ) {
        database.getReference("devices")
            .child(deviceId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.getValue(DeviceData::class.java)
                    if (data != null) {
                        onData(data)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onError(error.message)
                }
            })
    }

    fun fetchHistory(
        onData: (List<HistoryItem>) -> Unit,
        onError: (String) -> Unit
    ): ValueEventListener {
        val ref = database.getReference("history")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<HistoryItem>()

                for (child in snapshot.children) {
                    val title = child.child("title").getValue(String::class.java) ?: ""
                    val time = child.child("time").getValue(String::class.java) ?: ""
                    val subtitle = child.child("subtitle").getValue(String::class.java) ?: ""
                    val status = child.child("status").getValue(String::class.java) ?: "SAFE"

                    list.add(
                        HistoryItem(
                            title = title,
                            time = time,
                            subtitle = subtitle,
                            status = status
                        )
                    )
                }

                onData(list)
            }

            override fun onCancelled(error: DatabaseError) {
                onError(error.message)
            }
        }

        ref.addValueEventListener(listener)
        return listener
    }

    fun removeHistoryListener(listener: ValueEventListener) {
        database.getReference("history").removeEventListener(listener)
    }

    fun logout() {
        auth.signOut()
    }

    fun currentUserName(): String {
        return auth.currentUser?.displayName ?: "Ritik"
    }

    fun currentUserEmail(): String {
        return auth.currentUser?.email ?: "ritik@example.com"
    }

    fun addAsset(
        assetName: String,
        deviceId: String,
        minWeight: String,
        maxWeight: String,
        onResult: (Boolean, String) -> Unit
    ) {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            onResult(false, "User not logged in")
            return
        }

        val map = hashMapOf(
            "assetName" to assetName,
            "deviceId" to deviceId,
            "minWeight" to minWeight,
            "maxWeight" to maxWeight,
            "createdAt" to System.currentTimeMillis()
        )

        database.getReference("users")
            .child(uid)
            .child("assets")
            .child(deviceId)
            .setValue(map)
            .addOnSuccessListener { onResult(true, "Asset added successfully") }
            .addOnFailureListener { onResult(false, it.message ?: "Failed to add asset") }
    }
}