package com.itptit.roomrenting.data.repository

import android.util.Log
import com.itptit.roomrenting.data.remote.AuthApi
import com.itptit.roomrenting.data.remote.dto.LoginRequest
import com.itptit.roomrenting.data.remote.dto.LoginResponse
import com.itptit.roomrenting.data.remote.dto.RegisterRequest
import com.itptit.roomrenting.data.remote.dto.RegisterResponse
import com.itptit.roomrenting.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override fun login(loginRequest: LoginRequest): Flow<LoginResponse> {
        return flow {
            val response = authApi.login(loginRequest)
            emit(response)
        }
    }

    override fun register(registerRequest: RegisterRequest): Flow<RegisterResponse> {
        return flow {
            val response = authApi.register(registerRequest)
            emit(response)
        }
    }
}