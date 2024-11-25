package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.data.remote.dto.DistrictsResponse
import com.itptit.roomrenting.data.remote.dto.ProvincesResponse
import com.itptit.roomrenting.data.remote.dto.WardsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VietnamProvincesService {
    @GET("p")
    fun getProvinces(): Call<ProvincesResponse>

    @GET("p/{provinceId}?depth=2")
    fun getDistricts(@Path("provinceId") provinceId: String): Call<DistrictsResponse>

    @GET("d/{districtId}?depth=2")
    fun getWards(@Path("districtId") districtId: String): Call<WardsResponse>
}