package com.itptit.roomrenting.data.remote.dto.room

data class RoomRequest(
    val capacity: Int,
    val description: String,
    val name: String
)