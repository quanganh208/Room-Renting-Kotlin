package com.itptit.roomrenting.presentation.home.addresslocation

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiProvincesClient
import com.itptit.roomrenting.data.remote.dto.DistrictsResponse
import com.itptit.roomrenting.data.remote.dto.ProvincesResponse
import com.itptit.roomrenting.data.remote.dto.WardsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressLocationViewModel : ViewModel() {
    private val _provinces = MutableStateFlow(ProvincesResponse())
    val provinces: StateFlow<ProvincesResponse> = _provinces.asStateFlow()

    private val _districts = MutableStateFlow(
        DistrictsResponse(
            code = 0,
            codename = "",
            division_type = "",
            name = "",
            phone_code = 0,
            districts = emptyList()
        )
    )
    val districts: StateFlow<DistrictsResponse> = _districts.asStateFlow()

    private val _wards = MutableStateFlow(
        WardsResponse(
            code = 0,
            codename = "",
            division_type = "",
            name = "",
            province_code = 0,
            wards = emptyList()
        )
    )
    val wards: StateFlow<WardsResponse> = _wards.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getProvinces() {
        _isLoading.value = true
        try {
            ApiProvincesClient.provincesService.getProvinces()
                .enqueue(object : Callback<ProvincesResponse> {
                    override fun onResponse(
                        call: Call<ProvincesResponse>,
                        response: Response<ProvincesResponse>
                    ) {
                        _isLoading.value = false
                        _provinces.value = if (response.isSuccessful) {
                            response.body() ?: ProvincesResponse()
                        } else {
                            ProvincesResponse() // or handle error case appropriately
                        }
                    }

                    override fun onFailure(call: Call<ProvincesResponse>, t: Throwable) {
                        _isLoading.value = false
                        _provinces.value = ProvincesResponse()
                    }
                })
        } catch (e: Exception) {
            _isLoading.value = false
            _provinces.value = ProvincesResponse()
        }
    }

    fun getDistricts(provinceCode: String) {
        _isLoading.value = true
        try {
            ApiProvincesClient.provincesService.getDistricts(provinceCode)
                .enqueue(object : Callback<DistrictsResponse> {
                    override fun onResponse(
                        call: Call<DistrictsResponse>,
                        response: Response<DistrictsResponse>
                    ) {
                        _isLoading.value = false
                        _districts.value = if (response.isSuccessful) {
                            response.body() ?: DistrictsResponse(
                                code = 0,
                                codename = "",
                                division_type = "",
                                name = "",
                                phone_code = 0,
                                districts = emptyList()
                            )
                        } else {
                            DistrictsResponse(
                                code = 0,
                                codename = "",
                                division_type = "",
                                name = "",
                                phone_code = 0,
                                districts = emptyList()
                            )
                        }
                    }

                    override fun onFailure(call: Call<DistrictsResponse>, t: Throwable) {
                        _isLoading.value = false
                        _districts.value = DistrictsResponse(
                            code = 0,
                            codename = "",
                            division_type = "",
                            name = "",
                            phone_code = 0,
                            districts = emptyList()
                        )
                    }
                })
        } catch (e: Exception) {
            _isLoading.value = false
            _provinces.value = ProvincesResponse()
        }
    }

    fun getWards(districtCode: String) {
        _isLoading.value = true
        try {
            ApiProvincesClient.provincesService.getWards(districtCode)
                .enqueue(object : Callback<WardsResponse> {
                    override fun onResponse(
                        call: Call<WardsResponse>,
                        response: Response<WardsResponse>
                    ) {
                        _isLoading.value = false
                        _wards.value = if (response.isSuccessful) {
                            response.body() ?: WardsResponse(
                                code = 0,
                                codename = "",
                                division_type = "",
                                name = "",
                                province_code = 0,
                                wards = emptyList()
                            )
                        } else {
                            WardsResponse(
                                code = 0,
                                codename = "",
                                division_type = "",
                                name = "",
                                province_code = 0,
                                wards = emptyList()
                            )
                        }
                    }

                    override fun onFailure(call: Call<WardsResponse>, t: Throwable) {
                        _isLoading.value = false
                        _wards.value = WardsResponse(
                            code = 0,
                            codename = "",
                            division_type = "",
                            name = "",
                            province_code = 0,
                            wards = emptyList()
                        )
                    }
                })
        } catch (e: Exception) {
            _isLoading.value = false
            _wards.value = WardsResponse(
                code = 0,
                codename = "",
                division_type = "",
                name = "",
                province_code = 0,
                wards = emptyList()
            )
        }
    }
}