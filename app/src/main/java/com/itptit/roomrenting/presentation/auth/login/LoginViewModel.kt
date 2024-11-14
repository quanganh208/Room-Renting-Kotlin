package com.itptit.roomrenting.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itptit.roomrenting.data.remote.dto.LoginRequest
import com.itptit.roomrenting.domain.usecases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    var username: String = ""
    var password: String = ""
    var loginState: LoginState = LoginState.Idle

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> login()
        }
    }

    fun login() {
        viewModelScope.launch {
            loginState = LoginState.Loading
            try {
                val response = authUseCases.login(LoginRequest(username, password))
                loginState = LoginState.Success(response)
            } catch (e: Exception) {
                loginState = LoginState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class LoginEvent {
    object Login : LoginEvent()
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val data: Any) : LoginState()
    data class Error(val message: String) : LoginState()
}