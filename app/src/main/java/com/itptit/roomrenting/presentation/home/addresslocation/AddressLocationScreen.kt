package com.itptit.roomrenting.presentation.home.addresslocation

import com.itptit.roomrenting.presentation.common.FullScreenLoadingModal
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.home.rentalhouse.LabelWithAsterisk

@Composable
fun AddressLocationScreen(
    navController: NavController,
    viewModel: AddressLocationViewModel
) {
    var detailedAddress by remember { mutableStateOf("") }
    var selectedProvince by remember { mutableStateOf("Chọn tỉnh/thành phố") }
    var selectedDistrict by remember { mutableStateOf("Chọn quận/huyện") }
    var selectedWard by remember { mutableStateOf("Chọn phường/xã") }

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val provinces by viewModel.provinces.collectAsState(initial = emptyList())
    val districts by viewModel.districts.collectAsState()
    val wards by viewModel.wards.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProvinces()
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
                .verticalScroll(scrollState)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Địa chỉ",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /* Placeholder to balance layout */ }) {
                    Spacer(modifier = Modifier.size(24.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Warning Box
            WarningBox()

            Spacer(modifier = Modifier.height(16.dp))

            // Input fields
            LabelWithAsterisk("Tỉnh/Thành phố")

            // DropdownSelector for province
            DropdownSelector(
                label = "Tỉnh/Thành phố",
                selectedOption = selectedProvince,
                options = provinces.map { it.name },
                onOptionSelected = { selectedName ->
                    selectedProvince = selectedName
                    val selectedProvinceCode = provinces.find { it.name == selectedName }?.code ?: 0
                    viewModel.getDistricts(selectedProvinceCode.toString()) // Fetch districts based on selected province code
                    selectedDistrict = "Chọn quận/huyện"
                    selectedWard = "Chọn phường/xã"
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            LabelWithAsterisk("Quận/Huyện")
            DropdownSelector(
                label = "Quận/Huyện",
                selectedOption = selectedDistrict,
                options = districts.districts.map { it.name },
                onOptionSelected = { selected ->
                    selectedDistrict = selected
                    val selectedDistrictCode =
                        districts.districts.find { it.name == selected }?.code ?: 0
                    viewModel.getWards(selectedDistrictCode.toString()) // Fetch wards based on selected district code
                    selectedWard = "Chọn phường/xã"
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            LabelWithAsterisk("Phường/Xã")
            DropdownSelector(
                label = "Phường/Xã",
                selectedOption = selectedWard,
                options = wards.wards.map { it.name },
                onOptionSelected = { selectedWard = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            LabelWithAsterisk("Địa chỉ chi tiết")
            OutlinedTextField(
                value = detailedAddress,
                onValueChange = { detailedAddress = it },
                placeholder = { Text("Nhập số nhà, hẻm, đường") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút "Xác nhận địa chỉ & tiếp tục"
            Button(
                onClick = {
                    // Truyền dữ liệu về màn hình trước đó
                    navController.previousBackStackEntry?.savedStateHandle?.set("address", detailedAddress)
                    navController.previousBackStackEntry?.savedStateHandle?.set("province", selectedProvince)
                    navController.previousBackStackEntry?.savedStateHandle?.set("district", selectedDistrict)
                    navController.previousBackStackEntry?.savedStateHandle?.set("ward", selectedWard)
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = "Xác nhận địa chỉ & tiếp tục",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun WarningBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(width = 1.dp, color = Color(0xFFFFA000), shape = RoundedCornerShape(4.dp))
            .background(Color(0xFFFFF8E1))
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning",
            tint = Color(0xFFFFA000),
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Top)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Thông tin:",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = "Nếu địa chỉ của bạn không có trong danh sách bên dưới. Vui lòng liên hệ với nhân viên để được hỗ trợ!",
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun DropdownSelector(
    label: String,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .clickable { expanded = true }
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = label,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = selectedOption,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    color = if ("Chọn" in selectedOption) Color.Gray else Color.Black
                )
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (options.isEmpty()) {
                DropdownMenuItem(
                    onClick = { expanded = false },
                    text = { Text("Không có dữ liệu") }
                )
            } else {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        text = { Text(option) }
                    )
                }
            }
        }
    }
}
