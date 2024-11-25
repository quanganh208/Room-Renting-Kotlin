package com.itptit.roomrenting.presentation.auth.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.auth.LoginRequest
import com.itptit.roomrenting.data.remote.dto.auth.LoginResponse
import com.itptit.roomrenting.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
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
                    if (response.isSuccessful) {
                        _loginResult.value = "Đăng nhập thành công"
                        saveLoginInfo(response.body()!!)
                    } else {
                        _loginResult.value =
                            "Đăng nhập thất bại: ${if (response.code() == 400) "Sai tên đăng nhập hoặc mật khẩu" else "Lỗi không xác định"}"
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _isLoading.value = false
                    _loginResult.value = "Đăng nhập thất bại: ${t.message}"
                }
            })
        } catch (e: Exception) {
            _isLoading.value = false
            _loginResult.value = "Đăng nhập thất bại: ${e.localizedMessage}"
        }
    }

    private fun saveLoginInfo(response: LoginResponse) {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences(
                Constants.LOGIN_PREFS,
                Context.MODE_PRIVATE
            )
        val gson = Gson()
        val dataJson = gson.toJson(response.data)
        with(sharedPreferences.edit()) {
            putString(Constants.USER_DATA, dataJson)
            apply()
        }
    }
}
