package com.itptit.roomrenting.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.LoginRequest
import com.itptit.roomrenting.data.remote.dto.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableStateFlow("")
    val loginResult: StateFlow<String> = _loginResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(username: String, password: String) {
        _isLoading.value = true
        val request = LoginRequest(username, password)
        try {
            ApiClient.authService.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    _isLoading.value = false
                    _loginResult.value = if (response.isSuccessful) {
                        "Đăng nhập thành công, chuyển hướng tới màn hình chính"
                    } else {
                        "Đăng nhập thất bại: ${if (response.code() == 400) "Sai tên đăng nhập hoặc mật khẩu" else "Lỗi không xác định"}"
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _isLoading.value = false
                    _loginResult.value = "Error: ${t.message}"
                }
            })
        } catch (e: Exception) {
            _isLoading.value = false
            _loginResult.value = "Error: ${e.localizedMessage}"
        }
    }
}
