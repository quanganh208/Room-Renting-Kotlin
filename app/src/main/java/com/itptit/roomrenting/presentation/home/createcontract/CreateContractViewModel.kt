package com.itptit.roomrenting.presentation.home.createcontract

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.data.remote.dto.rented_room.RentedRoomRequest
import com.itptit.roomrenting.data.remote.dto.rented_room.RentedRoomResponse
import com.itptit.roomrenting.domain.model.FileInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateContractViewModel : ViewModel() {
    private val storage = FirebaseStorage.getInstance()

    private val _uploadState = MutableStateFlow<UploadState>(UploadState.Idle)

    private fun uploadImageToFirebase(fileInfo: FileInfo?, onSuccess: (String) -> Unit) {
        if (fileInfo == null) {
            _uploadState.value = UploadState.Error("Không có file nào được chọn.")
            return
        }

        viewModelScope.launch {
            try {
                _uploadState.value = UploadState.Loading
                val storageRef = storage.reference.child("contracts/${fileInfo.fileName}")
                val uploadTask = storageRef.putFile(fileInfo.uri)

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

    fun createRentedRoom(
        roomId: String,
        contractUrl: FileInfo?,
        electricityPrice: Int,
        endDate: String,
        generalPrice: Int,
        initElectricityNum: Int,
        initWaterNum: Int,
        internetPrice: Int,
        numberOfTenants: Int,
        paymentDay: Int,
        price: Int,
        startDate: String,
        tenantName: String,
        tenantPhone: String,
        waterPrice: Int
    ) {
        _isLoading.value = true
        uploadImageToFirebase(contractUrl) { downloadUrl: String ->
            val request = RentedRoomRequest(
                contractUrl = downloadUrl,
                electricityPrice = electricityPrice,
                endDate = endDate,
                generalPrice = generalPrice,
                initElectricityNum = initElectricityNum,
                initWaterNum = initWaterNum,
                internetPrice = internetPrice,
                numberOfTenants = numberOfTenants,
                paymentDay = paymentDay,
                price = price,
                startDate = startDate,
                tenantName = tenantName,
                tenantPhone = tenantPhone,
                waterPrice = waterPrice
            )
            ApiClient.rentedRoomService.createRentedRoom(roomId, request)
                .enqueue(object : Callback<RentedRoomResponse> {
                    override fun onResponse(
                        call: Call<RentedRoomResponse>,
                        response: Response<RentedRoomResponse>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            _result.value = "Tạo hợp đồng thành công"
                        } else {
                            _result.value =
                                "Tạo hợp đồng thất bại: ${response.body()?.message}"
                        }
                    }

                    override fun onFailure(call: Call<RentedRoomResponse>, t: Throwable) {
                        _isLoading.value = false
                        _result.value = "Tạo hợp đồng thất bại: ${t.message}"
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