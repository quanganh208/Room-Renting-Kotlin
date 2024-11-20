package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.data.remote.dto.house.AllHouseResponse
import com.itptit.roomrenting.data.remote.dto.house.HouseRequest
import com.itptit.roomrenting.data.remote.dto.house.HouseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HouseService {
    @GET("houses")
    fun getHouses(): Call<AllHouseResponse>

    @POST("houses")
    fun createHouse(@Body request: HouseRequest): Call<HouseResponse>

    @GET("/houses/{houseId}")
    fun getHouseDetails(@Path("houseId") houseId: String): HouseResponse

}