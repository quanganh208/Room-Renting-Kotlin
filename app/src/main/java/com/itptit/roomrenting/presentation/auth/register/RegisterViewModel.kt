package com.itptit.roomrenting.presentation.auth.register

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.auth.RegisterRequest
import com.itptit.roomrenting.data.remote.dto.auth.RegisterResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val _registerResult = MutableStateFlow("")
    val registerResult: StateFlow<String> = _registerResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun register(username: String, password: String, fullName: String, email: String, phone: String) {
        _isLoading.value = true
        val request = RegisterRequest(username, password, fullName, email, phone)
        try {
            ApiClient.authService.register(request).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    _isLoading.value = false
                    _registerResult.value = if (response.body()?.success == true) {
                        "Đăng ký thành công"
                    } else {
                        "Đăng ký thất bại: ${if (response.code() == 400) "Tên đăng nhập hoặc email đã có người sử dụng" else "Lỗi không xác định"}"
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    _isLoading.value = false
                    _registerResult.value = "Error: ${t.message}"
                }
            })
        } catch (e: Exception) {
            _isLoading.value = false
            _registerResult.value = "Error: ${e.localizedMessage}"
        }
    }
}