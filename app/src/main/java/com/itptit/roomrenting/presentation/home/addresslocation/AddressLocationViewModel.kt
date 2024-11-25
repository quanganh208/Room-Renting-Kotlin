package com.itptit.roomrenting.presentation.home.addresslocation

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiProvincesClient
import com.itptit.roomrenting.data.remote.dto.ProvincesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressLocationViewModel : ViewModel() {
    private val _provinces = MutableStateFlow(ProvincesResponse())
    val result: StateFlow<ProvincesResponse> = _provinces.asStateFlow()

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
}