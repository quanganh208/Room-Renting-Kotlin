package com.itptit.roomrenting.data.remote.roomrenting

import com.itptit.roomrenting.data.remote.dto.house.AllHouseResponse
import com.itptit.roomrenting.data.remote.dto.house.HouseRequest
import com.itptit.roomrenting.data.remote.dto.house.HouseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HouseService {
    @GET("houses")
    fun getAllHouses(): Call<AllHouseResponse>

    @POST("houses")
    fun createHouse(@Body request: HouseRequest): Call<HouseResponse>

    @GET("houses/{houseId}")
    fun getHouse(@Path("houseId") houseId: String): Call<HouseResponse>

    @PUT("owner/houses/{houseId}")
    fun updateHouse(@Path("houseId") houseId: String, @Body request: HouseRequest): Call<HouseResponse>

    @DELETE("owner/houses/{houseId}")
    fun deleteHouse(@Path("houseId") houseId: String): Call<HouseResponse>
}