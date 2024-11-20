package com.itptit.roomrenting.presentation.auth.home

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.house.AllHouseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _houses = MutableStateFlow("")
    val houses: StateFlow<String> = _houses

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getHouses() {
        _isLoading.value = true
        ApiClient.houseService.getHouses().enqueue(object : Callback<AllHouseResponse> {
            override fun onResponse(call: Call<AllHouseResponse>, response: Response<AllHouseResponse>) {
                _isLoading.value = false
                _houses.value = (response.body()?.toString() ?: "No houses found")
            }

            override fun onFailure(call: Call<AllHouseResponse>, t: Throwable) {
                _isLoading.value = false
                _houses.value = "Error: ${t.message}"
            }
        })
    }
}