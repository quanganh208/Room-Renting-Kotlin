package com.itptit.roomrenting.presentation.home

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.house.HouseResponse
import com.itptit.roomrenting.data.remote.dto.room.AllRoomResponse
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
        ApiClient.roomService.getRoom(houseId = "4").enqueue(object : Callback<AllRoomResponse> {
            override fun onResponse(call: Call<AllRoomResponse>, response: Response<AllRoomResponse>) {
                _isLoading.value = false
                _houses.value = (response.body()?.toString() ?: "No houses found")
            }

            override fun onFailure(call: Call<AllRoomResponse>, t: Throwable) {
                _isLoading.value = false
                _houses.value = "Error: ${t.message}"
            }
        })
    }
}