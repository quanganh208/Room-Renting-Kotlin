package com.itptit.roomrenting.presentation.home.addasset

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.asset.AssetRequest
import com.itptit.roomrenting.data.remote.dto.asset.AssetResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAssetViewModel : ViewModel() {
    private val storage = FirebaseStorage.getInstance()

    private val _uploadState = MutableStateFlow<UploadState>(UploadState.Idle)

    private fun uploadImageToFirebase(uri: Uri?, onSuccess: (String) -> Unit) {
        if (uri == null) {
            _uploadState.value = UploadState.Error("Không có ảnh nào được chọn.")
            return
        }

        viewModelScope.launch {
            try {
                _uploadState.value = UploadState.Loading
                val storageRef = storage.reference.child("assets/${System.currentTimeMillis()}.jpg")
                val uploadTask = storageRef.putFile(uri)

                uploadTask.addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                        _uploadState.value = UploadState.Success(downloadUrl.toString())
                        onSuccess(downloadUrl.toString())
                    }
                }.addOnFailureListener { exception ->
                    _uploadState.value = UploadState.Error(exception.message ?: "Đã xảy ra lỗi.")
                }
            } catch (e: Exception) {
                _uploadState.value = UploadState.Error(e.message ?: "Đã xảy ra lỗi.")
            }
        }
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    fun createAsset(
        roomId: String,
        imageUrl: Uri?,
        name: String
    ) {
        _isLoading.value = true
        uploadImageToFirebase(imageUrl) { downloadUrl ->
            val request = AssetRequest(downloadUrl, name)
            ApiClient.assetService.createAsset(roomId, request)
                .enqueue(object : Callback<AssetResponse> {
                    override fun onResponse(
                        call: Call<AssetResponse>,
                        response: Response<AssetResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            _result.value = "Tạo tài sản thành công"
                        } else {
                            _result.value =
                                "Tạo tài sản thất bại: ${if (response.code() == 400) "Sai thông tin" else "Lỗi không xác định"}"
                        }
                    }

                    override fun onFailure(call: Call<AssetResponse>, t: Throwable) {
                        _isLoading.value = false
                        _result.value = "Tạo tài sản thất bại: ${t.message}"
                    }
                })
        }
    }

    sealed class UploadState {
        object Idle : UploadState()
        object Loading : UploadState()
        data class Success(val downloadUrl: String) : UploadState()
        data class Error(val message: String) : UploadState()
    }
}
