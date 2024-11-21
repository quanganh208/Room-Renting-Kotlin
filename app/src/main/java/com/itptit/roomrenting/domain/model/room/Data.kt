package com.itptit.roomrenting.domain.model.room

data class Data(
    val capacity: Int,
    val createdAt: String,
    val description: String,
    val house: House,
    val id: Int,
    val name: String,
    val updatedAt: String
)