package com.itptit.roomrenting.presentation.room

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.room.AllRoomResponse
import com.itptit.roomrenting.data.remote.dto.room.RoomResponse
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

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    fun updateResult(result: String) {
        _result.value = result
    }

    fun deleteRoom(
        houseId: String,
        roomId: String
    ) {
        _isLoading.value = true
        try {
            ApiClient.roomService.deleteRoom(houseId = houseId, roomId = roomId)
                .enqueue(object : Callback<RoomResponse> {
                    override fun onResponse(
                        call: Call<RoomResponse>,
                        response: Response<RoomResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            _result.value = "Xoá phòng trọ thành công"
                        } else {
                            _result.value =
                                "Xoá phòng trọ thất bại: ${if (response.code() == 400) "Sai thông tin" else "Lỗi không xác định"}"
                        }
                    }

                    override fun onFailure(call: Call<RoomResponse>, t: Throwable) {
                        _isLoading.value = false
                        _result.value = "Xoá phòng trọ thất bại: ${t.message}"
                    }
                })
        } catch (e: Exception) {
            _isLoading.value = false
            _result.value = "Xóa phòng trọ thất bại: ${e.localizedMessage}"
        }
    }
}