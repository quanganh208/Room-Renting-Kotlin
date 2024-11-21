package com.itptit.roomrenting.domain.model.invoice

import com.itptit.roomrenting.domain.model.RentedRoom

data class Data(
    val createdAt: String,
    val dueDate: String,
    val electricityNum: Int,
    val electricityPrice: Int,
    val generalPrice: Int,
    val id: Int,
    val internetPrice: Int,
    val paymentDate: Any,
    val rentedRoom: RentedRoom,
    val roomPrice: Int,
    val totalPrice: Any,
    val updatedAt: String,
    val waterNum: Int,
    val waterPrice: Int
)