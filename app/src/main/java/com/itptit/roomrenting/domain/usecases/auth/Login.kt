package com.itptit.roomrenting.domain.usecases.auth

import com.itptit.roomrenting.data.remote.dto.LoginRequest
import com.itptit.roomrenting.data.remote.dto.LoginResponse
import com.itptit.roomrenting.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class Login(
    private val authRepository : AuthRepository
) {
    operator fun invoke(loginRequest: LoginRequest): Flow<LoginResponse> {
        return authRepository.login(loginRequest)

    }
}