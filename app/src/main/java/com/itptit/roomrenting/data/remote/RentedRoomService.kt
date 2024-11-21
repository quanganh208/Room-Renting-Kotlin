package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.data.remote.dto.rented_room.AllRentedRoomResponse
import com.itptit.roomrenting.data.remote.dto.rented_room.RentedRoomRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RentedRoomService {
    @GET("/owner/rooms/{roomId}/rented-rooms")
    fun getAllRentedRooms(@Path("roomId") roomId: String): Call<AllRentedRoomResponse>

    @GET("/owner/rooms/{roomId}/rented-rooms/{rentedRoomId}")
    fun getRentedRoom(
        @Path("roomId") roomId: String,
        @Path("rentedRoomId") rentedRoomId: String
    ): Call<RentedRoomService>

    @POST("/owner/rooms/{roomId}/rented-rooms")
    fun createRentedRoom(
        @Path("roomId") roomId: String,
        @Body request: RentedRoomRequest
    ): Call<RentedRoomService>

    @DELETE("/owner/rooms/{roomId}/rented-rooms/{rentedRoomId}")
    fun deleteRentedRoom(
        @Path("roomId") roomId: String,
        @Path("rentedRoomId") rentedRoomId: String
    ): Call<RentedRoomService>
}