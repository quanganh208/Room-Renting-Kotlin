package com.itptit.roomrenting.data.remote.dto

import com.itptit.roomrenting.domain.model.Data

data class RegisterResponse(
    val data: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)