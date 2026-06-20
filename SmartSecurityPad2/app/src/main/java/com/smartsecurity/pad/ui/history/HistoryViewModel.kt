package com.smartsecurity.pad.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartsecurity.pad.data.model.HistoryItem
import com.smartsecurity.pad.data.repository.FirebaseRepository
import com.google.firebase.database.ValueEventListener

class HistoryViewModel(
    private val repository: FirebaseRepository = FirebaseRepository()
) : ViewModel() {

    private val _historyList = MutableLiveData<List<HistoryItem>>()
    val historyList: LiveData<List<HistoryItem>> = _historyList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var listener: ValueEventListener? = null

    fun loadHistory() {
        listener = repository.fetchHistory(
            onData = { list -> _historyList.postValue(list) },
            onError = { message -> _error.postValue(message) }
        )
    }

    override fun onCleared() {
        super.onCleared()
        listener?.let { repository.removeHistoryListener(it) }
    }
}
