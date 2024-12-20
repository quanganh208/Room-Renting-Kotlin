package com.itptit.roomrenting.data.remote.dto.auth

import com.itptit.roomrenting.domain.model.auth.register.Data

data class RegisterResponse(
    val data: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)