package com.itptit.roomrenting.data.remote.dto.room

import com.itptit.roomrenting.domain.model.room.Data

data class AllRoomResponse(
    val data: List<Data>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)