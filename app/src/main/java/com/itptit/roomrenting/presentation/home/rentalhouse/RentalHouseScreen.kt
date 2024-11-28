package com.itptit.roomrenting.presentation.home.rentalhouse

import android.net.Uri
import android.util.Log
import com.itptit.roomrenting.presentation.common.FullScreenLoadingModal
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.navgraph.Route

@Composable
fun RentalHouseScreen(
    navController: NavController,
    viewModel: RentalHouseViewModel,
    houseId: String
) {
    val hostelName by viewModel.hostelName.collectAsState()
    val floorCount by viewModel.floorCount.collectAsState()
    val address by viewModel.address.collectAsState()
    val province by viewModel.province.collectAsState()
    val district by viewModel.district.collectAsState()
    val ward by viewModel.ward.collectAsState()
    val result by viewModel.result.collectAsState()

    var isDialogVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val isButtonEnabled =
        hostelName.isNotEmpty() && floorCount.isNotEmpty() && address.isNotEmpty() && province.isNotEmpty() && district.isNotEmpty() && ward.isNotEmpty()

    LaunchedEffect(houseId) {
        if (houseId != "0") {
            viewModel.getHouseById(houseId)
        }
    }

    LaunchedEffect(result) {
        if (result.isNotEmpty()) {
            isDialogVisible = true
        }
    }

    LaunchedEffect(navController.currentBackStackEntry?.savedStateHandle) {
        val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<String>("address")?.observeForever { newAddress ->
            viewModel.updateAddress(newAddress ?: "")
        }
        savedStateHandle?.getLiveData<String>("province")?.observeForever { newProvince ->
            viewModel.updateProvince(newProvince ?: "")
        }
        savedStateHandle?.getLiveData<String>("district")?.observeForever { newDistrict ->
            viewModel.updateDistrict(newDistrict ?: "")
        }
        savedStateHandle?.getLiveData<String>("ward")?.observeForever { newWard ->
            viewModel.updateWard(newWard ?: "")
        }
    }

    FullScreenLoadingModal(isVisible = viewModel.isLoading.collectAsState().value)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    focusManager.clearFocus()
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues())
        ) {
            // Header
            Text(
                text = if (houseId != "0") "Cập nhật nhà cho thuê" else "Thêm mới nhà cho thuê",
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Thông tin nhà cho thuê
            SectionHeader(
                title = "Thông tin nhà cho thuê",
                description = "Thông tin cơ bản tên, loại hình..."
            )

            // Loại hình cho thuê
            LabelWithAsterisk(label = "Loại hình cho thuê")
            OutlinedTextField(
                value = "Nhà trọ",
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { /* Xử lý khi cần */ }) {
                        Icon(Icons.Default.Clear, contentDescription = null)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tên nhà trọ
            LabelWithAsterisk(label = "Tên nhà trọ")
            OutlinedTextField(
                value = hostelName,
                onValueChange = { viewModel.updateHostelName(it) },
                placeholder = { Text("Ví dụ: Đức Sơn") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .background(Color(0xFFF0F0F0), shape = MaterialTheme.shapes.small)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Nhà trọ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                },
                singleLine = true,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Thiết lập số tầng
            LabelWithAsterisk(label = "Thiết lập số tầng")
            OutlinedTextField(
                value = floorCount,
                onValueChange = { viewModel.updateFloorCount(it) },
                placeholder = { Text("Ví dụ: 3") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    if (floorCount.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateFloorCount("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "Xóa nội dung")
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Địa chỉ & vị trí
            SectionHeader(
                title = "Địa chỉ & vị trí",
                description = "Địa chỉ giúp khách tìm đến chính xác để xem nhà..."
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    navController.navigate(
                        Route.AddressLocationScreen.route
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pluscircle_icon), // Sử dụng ảnh từ drawable
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = if (houseId != "0") "Chỉnh sửa địa chỉ" else "Chọn địa chỉ",
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium, // Đặt font chữ đậm vừa
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (address.isNotEmpty() && ward.isNotEmpty() && district.isNotEmpty() && province.isNotEmpty()) {
                    "Địa chỉ đã chọn: $address, $ward, $district, $province"
                } else {
                    "Chưa có địa chỉ được chọn"
                },
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (houseId != "0") {
                        viewModel.updateHouse(
                            houseId = houseId,
                            addressLine = address,
                            city = province,
                            district = district,
                            floorCount = floorCount.toInt(),
                            name = hostelName,
                            ward = ward
                        )
                    } else {
                        viewModel.createHouse(
                            addressLine = address,
                            city = province,
                            district = district,
                            floorCount = floorCount.toInt(),
                            name = hostelName,
                            ward = ward
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) Color(0xFF0B9E43) else Color(0xFFA5D6A7),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(6.dp),
            ) {
                Text(
                    text = if (houseId != "0") "Cập nhật nhà trọ" else "Tạo nhà trọ ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = { isDialogVisible = false },
            title = { Text("Thông báo") },
            text = { Text(result) },
            confirmButton = {
                Button(onClick = {
                    isDialogVisible = false
                    if ("thành công" in result) {
                        navController.navigate(Route.HomeScreen.route)
                    }
                }) {
                    Text("OK")
                }
            }
        )
    }
}


@Composable
fun SectionHeader(title: String, description: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Icon Box for #
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF4CAF50), shape = MaterialTheme.shapes.small),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "#",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Title and description
        Column {
            Text(
                text = title,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun LabelWithAsterisk(label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = "*",
            color = Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}