package com.itptit.roomrenting.domain.model.asset

import com.itptit.roomrenting.domain.model.Room

data class Data(
    val createdAt: String,
    val id: Int,
    val imageUrl: String,
    val name: String,
    val room: Room,
    val updatedAt: String
)