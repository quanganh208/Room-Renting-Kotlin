package com.itptit.roomrenting.presentation.home.main

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.house.HouseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeMainViewModel : ViewModel() {
    private val _deleteResult = MutableStateFlow("")
    val deleteResult: StateFlow<String> = _deleteResult

    fun updateDeleteResult(newResult: String) {
        _deleteResult.value = newResult
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun deleteHouse(houseId: String) {
        _isLoading.value = true
        try {
            ApiClient.houseService.deleteHouse(houseId).enqueue(object : Callback<HouseResponse> {
                override fun onResponse(
                    call: Call<HouseResponse>,
                    response: Response<HouseResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _deleteResult.value = "Xóa nhà thành công"
                    } else {
                        _deleteResult.value = "Xóa nhà thất bại: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<HouseResponse>, t: Throwable) {
                    _isLoading.value = false
                    _deleteResult.value = "Xóa nhà thất bại: ${t.message}"
                }
            })
        } catch (e: Exception) {
            _isLoading.value = false
            _deleteResult.value = "Xóa nhà thất bại: ${e.localizedMessage}"
        }
    }
}