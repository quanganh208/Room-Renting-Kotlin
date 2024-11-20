package com.itptit.roomrenting.data.remote.dto.house

import com.itptit.roomrenting.domain.model.house.Data

data class AllHouseResponse(
    val data: List<Data>,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)