package com.itptit.roomrenting.presentation.room.asset

import androidx.lifecycle.ViewModel
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.asset.AllAssetResponse
import com.itptit.roomrenting.domain.model.asset.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssetRoomViewModel() : ViewModel() {

    private val _assets = MutableStateFlow<List<Data>>(emptyList())
    val assets: StateFlow<List<Data>> = _assets

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getAllAssets(roomId: String) {
        _isLoading.value = true
        ApiClient.assetService.getAllAssets(roomId).enqueue(object : Callback<AllAssetResponse> {
            override fun onResponse(call: Call<AllAssetResponse>, response: Response<AllAssetResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _assets.value = response.body()?.data ?: emptyList()
                } else {
                    _error.value = "Failed to load assets: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<AllAssetResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = "Failed to load assets: ${t.message}"
            }
        })
    }
}