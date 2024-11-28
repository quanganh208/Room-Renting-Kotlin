package com.itptit.roomrenting.presentation.home.rentalhouse

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.house.HouseRequest
import com.itptit.roomrenting.data.remote.dto.house.HouseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RentalHouseViewModel : ViewModel() {
    private val _hostelName = MutableStateFlow("")
    val hostelName: StateFlow<String> = _hostelName

    private val _floorCount = MutableStateFlow("")
    val floorCount: StateFlow<String> = _floorCount

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address

    private val _province = MutableStateFlow("")
    val province: StateFlow<String> = _province

    private val _district = MutableStateFlow("")
    val district: StateFlow<String> = _district

    private val _ward = MutableStateFlow("")
    val ward: StateFlow<String> = _ward

    fun updateHostelName(newName: String) {
        _hostelName.value = newName
    }

    fun updateFloorCount(newCount: String) {
        _floorCount.value = newCount
    }

    fun updateAddress(newAddress: String) {
        _address.value = newAddress
    }

    fun updateProvince(newProvince: String) {
        _province.value = newProvince
    }

    fun updateDistrict(newDistrict: String) {
        _district.value = newDistrict
    }

    fun updateWard(newWard: String) {
        _ward.value = newWard
    }

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun createHouse(
        addressLine: String,
        city: String,
        district: String,
        floorCount: Int,
        name: String,
        ward: String
    ) {
        _isLoading.value = true
        val request = HouseRequest(
            addressLine = addressLine,
            city = city,
            district = district,
            floorCount = floorCount,
            name = name,
            ward = ward
        )
        try {
            ApiClient.houseService.createHouse(request).enqueue(object : Callback<HouseResponse> {
                override fun onResponse(
                    call: Call<HouseResponse>,
                    response: Response<HouseResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _result.value = "Tạo nhà trọ thành công"
                    } else {
                        _result.value =
                            "Tạo nhà trọ thất bại: ${if (response.code() == 400) "Sai thông tin" else "Lỗi không xác định"}"
                    }
                }

                override fun onFailure(call: Call<HouseResponse>, t: Throwable) {
                    _isLoading.value = false
                    _result.value = "Tạo nhà trọ thất bại: ${t.message}"
                }
            })
        } catch (e: Exception) {
            _isLoading.value = false
            _result.value = "Tạo nhà trọ thất bại: ${e.localizedMessage}"
        }
    }

    fun getHouseById(houseId: String) {
        _isLoading.value = true
        try {
            ApiClient.houseService.getHouse(houseId).enqueue(object : Callback<HouseResponse> {
                override fun onResponse(
                    call: Call<HouseResponse>,
                    response: Response<HouseResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _hostelName.value = response.body()?.data?.name ?: ""
                        _floorCount.value = response.body()?.data?.floorCount.toString()
                        _address.value = response.body()?.data?.addressLine ?: ""
                        _province.value = response.body()?.data?.city ?: ""
                        _district.value = response.body()?.data?.district ?: ""
                        _ward.value = response.body()?.data?.ward ?: ""
                    } else {
                        _result.value =
                            "Lấy thông tin nhà trọ thất bại: ${if (response.code() == 400) "Sai thông tin" else "Lỗi không xác định"}"
                    }
                }

                override fun onFailure(call: Call<HouseResponse>, t: Throwable) {
                    _isLoading.value = false
                    _result.value = "Lấy thông tin nhà trọ thất bại: ${t.message}"
                }
            })
        } catch (e: Exception) {
            _isLoading.value = false
            _result.value = "Lấy thông tin nhà trọ thất bại: ${e.localizedMessage}"
        }
    }

    fun updateHouse(
        houseId: String,
        addressLine: String,
        city: String,
        district: String,
        floorCount: Int,
        name: String,
        ward: String
    ) {
        _isLoading.value = true
        val request = HouseRequest(
            addressLine = addressLine,
            city = city,
            district = district,
            floorCount = floorCount,
            name = name,
            ward = ward
        )
        try {
            ApiClient.houseService.updateHouse(houseId, request).enqueue(object : Callback<HouseResponse> {
                override fun onResponse(
                    call: Call<HouseResponse>,
                    response: Response<HouseResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _result.value = "Cập nhật nhà trọ thành công"
                    } else {
                        _result.value =
                            "Cập nhật nhà trọ thất bại: ${if (response.code() == 400) "Sai thông tin" else "Lỗi không xác định"}"
                    }
                }

                override fun onFailure(call: Call<HouseResponse>, t: Throwable) {
                    _isLoading.value = false
                    _result.value = "Cập nhật nhà trọ thất bại: ${t.message}"
                }
            })
        } catch (e: Exception) {
            _isLoading.value = false
            _result.value = "Cập nhật nhà trọ thất bại: ${e.localizedMessage}"
        }
    }
}