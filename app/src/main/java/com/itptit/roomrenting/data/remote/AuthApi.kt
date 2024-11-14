package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.data.remote.dto.LoginRequest
import com.itptit.roomrenting.data.remote.dto.LoginResponse
import com.itptit.roomrenting.data.remote.dto.RegisterRequest
import com.itptit.roomrenting.data.remote.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse
}