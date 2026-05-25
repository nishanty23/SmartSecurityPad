package com.smartsecurity.pad.ui.add

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smartsecurity.pad.data.repository.FirebaseRepository
import com.smartsecurity.pad.databinding.ActivityAddAssetBinding
import com.smartsecurity.pad.ui.assets.AssetsActivity
import com.smartsecurity.pad.ui.dashboard.MainActivity
import com.smartsecurity.pad.ui.profile.ProfileActivity

class AddAssetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddAssetBinding
    private val repository = FirebaseRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAssetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topBar.ivBack.setOnClickListener { finish() }

        binding.btnAddAsset.setOnClickListener {
            val assetName = binding.etAssetName.text.toString().trim()
            val deviceId = binding.etDeviceId.text.toString().trim()
            val minWeight = binding.etMinWeight.text.toString().trim()
            val maxWeight = binding.etMaxWeight.text.toString().trim()

            if (assetName.isEmpty() || deviceId.isEmpty()) {
                Toast.makeText(this, "Please enter asset name and device ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            repository.addAsset(assetName, deviceId, minWeight, maxWeight) { success, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                if (success) finish()
            }
        }

        binding.bottomNav.navHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.bottomNav.navAssets.setOnClickListener { startActivity(Intent(this, AssetsActivity::class.java)) }
        binding.bottomNav.navAdd.setOnClickListener { }
        binding.bottomNav.navProfile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }
}