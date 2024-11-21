package com.itptit.roomrenting.domain.model.house

import com.itptit.roomrenting.domain.model.Authority

data class Owner(
    val authorities: List<Authority>,
    val createdAt: String,
    val fullName: String,
    val id: Int,
    val updatedAt: String,
    val username: String
)