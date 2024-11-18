package com.itptit.roomrenting.data.remote.dto

import com.itptit.roomrenting.domain.model.auth.login.Data

data class LoginResponse(
    val data: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)