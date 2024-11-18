package com.itptit.roomrenting.domain.model.auth.login

import com.itptit.roomrenting.domain.model.User

data class Data(
    val jwt: String,
    val user: User
)