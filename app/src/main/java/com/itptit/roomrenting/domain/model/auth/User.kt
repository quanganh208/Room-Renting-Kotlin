package com.itptit.roomrenting.domain.model.auth

import com.itptit.roomrenting.domain.model.Authority

data class User(
    val authorities: List<Authority>,
    val createdAt: String,
    val fullName: String,
    val phone: String,
    val email: String,
    val id: Int,
    val updatedAt: String,
    val username: String
)