package com.smartsecurity.pad.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartsecurity.pad.data.model.DeviceData
import com.smartsecurity.pad.data.repository.FirebaseRepository
import com.smartsecurity.pad.utils.Event

class DashboardViewModel : ViewModel() {

    private val repository = FirebaseRepository()

    private val _deviceData = MutableLiveData<DeviceData>()
    val deviceData: LiveData<DeviceData> = _deviceData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _showAlertEvent = MutableLiveData<Event<Unit>>()
    val showAlertEvent: LiveData<Event<Unit>> = _showAlertEvent

    private var lastStatus: String? = null

    fun startListening() {
        repository.listenToPad(
            onData = { data ->
                _deviceData.value = data

                if (lastStatus != null && lastStatus != data.status && data.status == "ALERT") {
                    _showAlertEvent.value = Event(Unit)
                }
                lastStatus = data.status
            },
            onError = {
                _error.value = it
            }
        )
    }

    fun logout() {
        repository.logout()
    }
}