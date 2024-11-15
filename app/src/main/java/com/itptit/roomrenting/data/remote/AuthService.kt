package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.data.remote.dto.LoginRequest
import com.itptit.roomrenting.data.remote.dto.LoginResponse
import com.itptit.roomrenting.data.remote.dto.RegisterRequest
import com.itptit.roomrenting.data.remote.dto.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>
}