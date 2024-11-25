package com.itptit.roomrenting.data.remote.dto

import com.itptit.roomrenting.domain.model.District

data class DistrictsResponse(
    val code: Int,
    val codename: String,
    val districts: List<District>,
    val division_type: String,
    val name: String,
    val phone_code: Int
)