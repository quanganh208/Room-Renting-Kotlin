package com.itptit.roomrenting.presentation.room

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.room.RoomRequest
import com.itptit.roomrenting.data.remote.dto.room.RoomResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRoomViewModel : ViewModel() {
    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun createRoom(
        houseId: String,
        name: String,
        capacity: Int,
        description: String,
    ) {
        _isLoading.value = true
        val request = RoomRequest(
            name = name,
            capacity = capacity,
            description = description
        )
        try {
            ApiClient.roomService.createRoom(houseId = houseId, request = request)
                .enqueue(object : Callback<RoomResponse> {
                    override fun onResponse(
                        call: Call<RoomResponse>,
                        response: Response<RoomResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            _result.value = "Tạo phòng trọ thành công"
                        } else {
                            _result.value =
                                "Tạo phòng trọ thất bại: ${if (response.code() == 400) "Sai thông tin" else "Lỗi không xác định"}"
                        }
                    }

                    override fun onFailure(call: Call<RoomResponse>, t: Throwable) {
                        _isLoading.value = false
                        _result.value = "Tạo phòng trọ thất bại: ${t.message}"
                    }
                })
        } catch (e: Exception) {
            _isLoading.value = false
            _result.value = "Tạo phòng trọ thất bại: ${e.localizedMessage}"
        }
    }
}