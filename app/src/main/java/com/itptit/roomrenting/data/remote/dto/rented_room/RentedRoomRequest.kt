package com.itptit.roomrenting.data.remote.dto.rented_room

data class RentedRoomRequest(
    val contractUrl: String,
    val electricityPrice: Int,
    val endDate: String,
    val generalPrice: Int,
    val initElectricityNum: Int,
    val initWaterNum: Int,
    val internetPrice: Int,
    val numberOfTenants: Int,
    val paymentDay: Int,
    val price: Int,
    val startDate: String,
    val tenantName: String,
    val tenantPhone: String,
    val waterPrice: Int
)