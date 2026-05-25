package com.smartsecurity.pad.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartsecurity.pad.data.repository.AuthRepository
import com.smartsecurity.pad.utils.Event
import com.smartsecurity.pad.utils.UiState

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _loginState = MutableLiveData<UiState<Boolean>>()
    val loginState: LiveData<UiState<Boolean>> = _loginState

    private val _navigateToDashboard = MutableLiveData<Event<Boolean>>()
    val navigateToDashboard: LiveData<Event<Boolean>> = _navigateToDashboard

    fun checkSession() {
        if (authRepository.currentUser() != null) {
            _navigateToDashboard.value = Event(true)
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = UiState.Error("Email and password are required")
            return
        }

        _loginState.value = UiState.Loading
        authRepository.login(email, password) { success, error ->
            _loginState.value = if (success) {
                _navigateToDashboard.postValue(Event(true))
                UiState.Success(true)
            } else {
                UiState.Error(error ?: "Login failed")
            }
        }
    }
}