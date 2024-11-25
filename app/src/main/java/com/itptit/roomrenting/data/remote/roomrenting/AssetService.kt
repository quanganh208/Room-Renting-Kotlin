package com.itptit.roomrenting.data.remote.roomrenting

import com.itptit.roomrenting.data.remote.dto.asset.AllAssetResponse
import com.itptit.roomrenting.data.remote.dto.asset.AssetResponse
import com.itptit.roomrenting.data.remote.dto.house.HouseRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AssetService {
    @GET("/owner/rooms/{roomId}/assets")
    fun getAllAssets(@Path("roomId") roomId: String): Call<AllAssetResponse>

    @GET("/owner/rooms/{roomId}/assets/{assetId}")
    fun getAsset(
        @Path("roomId") roomId: String,
        @Path("assetId") assetId: String
    ): Call<AssetResponse>

    @POST("/owner/rooms/{roomId}/assets")
    fun createAsset(
        @Path("roomId") roomId: String, @Body request: HouseRequest
    ): Call<AssetResponse>

    @PUT("/owner/rooms/{roomId}/assets/{assetId}")
    fun updateAsset(
        @Path("roomId") roomId: String,
        @Path("assetId") assetId: String,
        @Body request: HouseRequest
    ): Call<AssetResponse>

    @DELETE("/owner/rooms/{roomId}/assets/{assetId}")
    fun deleteAsset(
        @Path("roomId") roomId: String,
        @Path("assetId") assetId: String
    ): Call<AssetResponse>
}