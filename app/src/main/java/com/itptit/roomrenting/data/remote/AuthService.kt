package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.data.remote.dto.auth.LoginRequest
import com.itptit.roomrenting.data.remote.dto.auth.LoginResponse
import com.itptit.roomrenting.data.remote.dto.auth.RegisterRequest
import com.itptit.roomrenting.data.remote.dto.auth.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}