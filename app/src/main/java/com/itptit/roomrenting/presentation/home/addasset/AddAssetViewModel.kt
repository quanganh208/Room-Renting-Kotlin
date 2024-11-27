package com.itptit.roomrenting.presentation.home.addasset

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddAssetViewModel : ViewModel() {
    private val storage = FirebaseStorage.getInstance()

    private val _uploadState = MutableStateFlow<UploadState>(UploadState.Idle)
    val uploadState: StateFlow<UploadState> = _uploadState

    fun uploadImageToFirebase(uri: Uri?) {
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
                    }
                }.addOnFailureListener { exception ->
                    _uploadState.value = UploadState.Error(exception.message ?: "Đã xảy ra lỗi.")
                }
            } catch (e: Exception) {
                _uploadState.value = UploadState.Error(e.message ?: "Đã xảy ra lỗi.")
            }
        }
    }

    sealed class UploadState {
        object Idle : UploadState()
        object Loading : UploadState()
        data class Success(val downloadUrl: String) : UploadState()
        data class Error(val message: String) : UploadState()
    }
}
