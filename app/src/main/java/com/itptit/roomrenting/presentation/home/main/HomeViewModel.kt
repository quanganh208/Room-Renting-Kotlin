package com.itptit.roomrenting.presentation.home.main

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.house.AllHouseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _houses = MutableStateFlow(
        AllHouseResponse(
            data = emptyList(),
            message = "",
            statusCode = 0,
            success = false
        )
    )
    val houses: StateFlow<AllHouseResponse> = _houses.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getOwnerHouses() {
        _isLoading.value = true
        ApiClient.houseService.getOwnerHouses().enqueue(object : Callback<AllHouseResponse> {
            override fun onResponse(
                call: Call<AllHouseResponse>,
                response: Response<AllHouseResponse>
            ) {
                _isLoading.value = false
                _houses.value = if (response.isSuccessful) {
                    response.body() ?: AllHouseResponse(
                        data = emptyList(),
                        message = "",
                        statusCode = 0,
                        success = false
                    )
                } else {
                    AllHouseResponse(
                        data = emptyList(),
                        message = "",
                        statusCode = 0,
                        success = false
                    )
                }
            }

            override fun onFailure(call: Call<AllHouseResponse>, t: Throwable) {
                _isLoading.value = false
                _houses.value = AllHouseResponse(
                    data = emptyList(),
                    message = "",
                    statusCode = 0,
                    success = false
                )
            }
        })
    }
}