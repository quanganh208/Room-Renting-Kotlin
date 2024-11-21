package com.itptit.roomrenting.domain.model

data class Room(
    val capacity: Int,
    val createdAt: String,
    val description: String,
    val house: House,
    val id: Int,
    val name: String,
    val updatedAt: String
)