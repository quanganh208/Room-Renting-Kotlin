package com.itptit.roomrenting.data.remote.dto.room

import com.itptit.roomrenting.domain.model.room.Data

data class RoomResponse(
    val data: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)