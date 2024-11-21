package com.itptit.roomrenting.data.remote.dto.rented_room

import com.itptit.roomrenting.domain.model.rented_room.Data

data class AllRentedRoomResponse(
    val data: List<Data>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)