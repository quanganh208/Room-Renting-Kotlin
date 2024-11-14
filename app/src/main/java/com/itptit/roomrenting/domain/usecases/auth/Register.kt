package com.itptit.roomrenting.domain.usecases.auth

import com.itptit.roomrenting.data.remote.dto.RegisterRequest
import com.itptit.roomrenting.data.remote.dto.RegisterResponse
import com.itptit.roomrenting.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class Register(
    private val authRepository : AuthRepository
) {
    operator fun invoke(registerRequest: RegisterRequest): Flow<RegisterResponse> {
        return authRepository.register(registerRequest)

    }
}