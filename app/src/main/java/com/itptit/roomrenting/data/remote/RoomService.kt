package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.data.remote.dto.room.AllRoomResponse
import com.itptit.roomrenting.data.remote.dto.room.RoomRequest
import com.itptit.roomrenting.data.remote.dto.room.RoomResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RoomService {
    @GET("owner/houses/{houseId}/rooms")
    fun getRoom(@Path("houseId") houseId: String): Call<AllRoomResponse>

    @POST("owner/houses/{houseId}/rooms")
    fun createRoom(
        @Path("houseId") houseId: String,
        @Body request: RoomRequest
    ): Call<RoomResponse>

    @PUT("owner/houses/{houseId}/rooms/{roomId}")
    fun updateRoom(
        @Path("houseId") houseId: String,
        @Path("roomId") roomId: String,
        @Body request: RoomRequest
    ): Call<RoomResponse>

    @DELETE("owner/houses/{houseId}/rooms/{roomId}")
    fun deleteRoom(
        @Path("houseId") houseId: String,
        @Path("roomId") roomId: String
    ): Call<RoomResponse>
}