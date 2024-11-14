package com.itptit.roomrenting.domain.model

data class User(
    val authorities: List<Authority>,
    val createdAt: String,
    val fullName: Any,
    val id: Int,
    val updatedAt: String,
    val username: String
)