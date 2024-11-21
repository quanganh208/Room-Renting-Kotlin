package com.itptit.roomrenting.domain.model.house

import com.itptit.roomrenting.domain.model.Owner

data class Data(
    val addressLine: String,
    val city: String,
    val createdAt: String,
    val district: String,
    val floorCount: Int,
    val id: Int,
    val name: String,
    val owner: Owner,
    val updatedAt: String,
    val ward: String
)