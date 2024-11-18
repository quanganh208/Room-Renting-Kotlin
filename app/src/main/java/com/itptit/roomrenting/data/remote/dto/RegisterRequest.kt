package com.itptit.roomrenting.data.remote.dto

data class RegisterRequest(
    val username: String,
    val password: String,
    val fullName: String,
    val email: String,
    val phone: String
)