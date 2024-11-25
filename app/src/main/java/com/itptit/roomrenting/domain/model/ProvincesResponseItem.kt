package com.itptit.roomrenting.domain.model

data class ProvincesResponseItem(
    val code: Int,
    val codename: String,
    val districts: List<Any>,
    val division_type: String,
    val name: String,
    val phone_code: Int
)