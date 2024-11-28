package com.itptit.roomrenting.domain.model.room

import com.itptit.roomrenting.domain.model.House

data class Data(
    val capacity: Int,
    val createdAt: String,
    val description: String,
    val house: House,
    val id: Int,
    val isCurrentlyRented: Boolean,
    val name: String,
    val updatedAt: String
)