package com.itptit.roomrenting.domain.model

data class RentedRoom(
    val contractUrl: String,
    val createdAt: String,
    val electricityPrice: Int,
    val endDate: String,
    val generalPrice: Int,
    val id: Int,
    val initElectricityNum: Int,
    val initWaterNum: Int,
    val internetPrice: Int,
    val numberOfTenants: Int,
    val paymentDay: Int,
    val price: Int,
    val room: Room,
    val startDate: String,
    val tenantName: String,
    val tenantPhone: String,
    val updatedAt: String,
    val waterPrice: Int
)