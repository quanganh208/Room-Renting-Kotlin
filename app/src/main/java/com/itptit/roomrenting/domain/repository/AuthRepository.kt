package com.itptit.roomrenting.domain.repository

import com.itptit.roomrenting.data.remote.dto.LoginRequest
import com.itptit.roomrenting.data.remote.dto.LoginResponse
import com.itptit.roomrenting.data.remote.dto.RegisterRequest
import com.itptit.roomrenting.data.remote.dto.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(loginRequest: LoginRequest): Flow<LoginResponse>
    fun register(registerRequest: RegisterRequest): Flow<RegisterResponse>
}