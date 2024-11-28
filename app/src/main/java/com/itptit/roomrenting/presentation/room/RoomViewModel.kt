package com.itptit.roomrenting.presentation.room

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.room.AllRoomResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomViewModel : ViewModel() {
    private val _rooms = MutableStateFlow(
        AllRoomResponse(
            data = emptyList(),
            message = "",
            statusCode = 0,
            success = false
        )
    )
    val rooms: StateFlow<AllRoomResponse> = _rooms.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getRooms(houseId: String) {
        _isLoading.value = true
        ApiClient.roomService.getRoom(houseId).enqueue(object : Callback<AllRoomResponse> {
            override fun onResponse(
                call: Call<AllRoomResponse>,
                response: Response<AllRoomResponse>
            ) {
                _isLoading.value = false
                _rooms.value = if (response.isSuccessful) {
                    response.body() ?: AllRoomResponse(
                        data = emptyList(),
                        message = "",
                        statusCode = 0,
                        success = false
                    )
                } else {
                    AllRoomResponse(
                        data = emptyList(),
                        message = "",
                        statusCode = 0,
                        success = false
                    )
                }
            }

            override fun onFailure(call: Call<AllRoomResponse>, t: Throwable) {
                _isLoading.value = false
                _rooms.value = AllRoomResponse(
                    data = emptyList(),
                    message = "",
                    statusCode = 0,
                    success = false
                )
            }
        }
        )
    }
}