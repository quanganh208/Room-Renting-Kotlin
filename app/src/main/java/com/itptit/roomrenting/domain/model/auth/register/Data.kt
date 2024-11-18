package com.itptit.roomrenting.domain.model.auth.register

import com.itptit.roomrenting.domain.model.Authority

data class Data(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val authorities: List<Authority>,
    val createdAt: String,
    val credentialsNonExpired: Boolean,
    val email: String,
    val enabled: Boolean,
    val fullName: String,
    val id: Int,
    val password: String,
    val phone: String,
    val updatedAt: String,
    val username: String
)