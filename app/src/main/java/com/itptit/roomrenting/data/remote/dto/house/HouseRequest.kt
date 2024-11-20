package com.itptit.roomrenting.data.remote.dto.house

data class HouseRequest(
    val addressLine: String,
    val city: String,
    val district: String,
    val floorCount: Int,
    val name: String,
    val ward: String
)