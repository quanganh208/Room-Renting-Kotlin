package com.itptit.roomrenting.data.remote.dto

import com.itptit.roomrenting.domain.model.Ward

data class WardsResponse(
    val code: Int,
    val codename: String,
    val division_type: String,
    val name: String,
    val province_code: Int,
    val wards: List<Ward>
)