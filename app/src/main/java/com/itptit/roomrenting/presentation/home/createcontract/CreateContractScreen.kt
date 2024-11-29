package com.itptit.roomrenting.presentation.home.createcontract

import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*

@Composable
fun CreateContractScreen(navController: NavController, roomId: String) {
    Scaffold(
        topBar = { CreateContractTopBar(navController) },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Cách nhau giữa các phần tử
            ) {
                item {
                    CreateContractContent()
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateTopPadding()
            )
    )
}


@Composable
fun ContractSectionHeader(title: String, description: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Dấu # trong hình vuông màu xanh lá
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFF4CAF50), shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "#",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(12.dp))

        // Tiêu đề và mô tả
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun CreateContractContent() {
    // Khai báo các biến trạng thái cho các trường nhập liệu
    var totalMembers by remember { mutableStateOf("") }
    var customerName by remember { mutableStateOf("") }
    var customerPhone by remember { mutableStateOf("") }

    // **New State Variable for Payment Day**
    var paymentDay by remember { mutableStateOf("") }

    // Existing state variables for payment amounts
    var electricityPrice by remember { mutableStateOf("") }
    var waterPrice by remember { mutableStateOf("") }
    var internetPrice by remember { mutableStateOf("") }
    var roomPrice by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header phần thông tin thời hạn hợp đồng
        ContractSectionHeader(
            title = "Thông tin thời hạn hợp đồng",
            description = "Giúp quản lý hợp đồng, làm văn bản hợp đồng."
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Ngày vào ở và ngày kết thúc với Date Pickers
        DateInputFields()

        Spacer(modifier = Modifier.height(24.dp))

        // Header phần thông tin khách thuê
        ContractSectionHeader(
            title = "Thông tin khách thuê",
            description = "Quét mã QR thẻ căn cước, khách cũ."
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tổng số thành viên
        LabelText(text = "Tổng số thành viên")
        Spacer(modifier = Modifier.height(8.dp))
        CustomInputField(
            value = totalMembers,
            onValueChange = { input ->
                // Chỉ cho phép nhập số
                if (input.all { it.isDigit() }) {
                    totalMembers = input
                }
            },
            placeholder = "",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tên khách và SĐT ZALO
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                LabelText(text = "Tên khách")
                Spacer(modifier = Modifier.height(8.dp))
                CustomInputField(
                    value = customerName,
                    onValueChange = { customerName = it },
                    placeholder = "",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                LabelText(text = "SĐT khách (ZALO)")
                Spacer(modifier = Modifier.height(8.dp))
                CustomInputField(
                    value = customerPhone,
                    onValueChange = { input ->
                        // Chỉ cho phép nhập số
                        if (input.all { it.isDigit() }) {
                            customerPhone = input
                        }
                    },
                    placeholder = "",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // **New Section Header for Payment Information**
        ContractSectionHeader(
            title = "Thông tin thanh toán",
            description = "Nhập thông tin về thanh toán các khoản phí."
        )
        Spacer(modifier = Modifier.height(16.dp))

        // **Ngày thanh toán as a Numeric Input Field**
        LabelText(text = "Ngày thanh toán")
        Spacer(modifier = Modifier.height(8.dp))
        CustomInputField(
            value = paymentDay,
            onValueChange = { input ->
                // Chỉ cho phép nhập số và giá trị từ 1 đến 31
                if (input.all { it.isDigit() } && (input.isEmpty() || (input.toIntOrNull()
                        ?: 0) in 1..31)) {
                    paymentDay = input
                }
            },
            placeholder = "Nhập ngày thanh toán (1-31)",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Giá tiền điện, nước, mạng, phòng
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                LabelText(text = "Giá tiền điện")
                Spacer(modifier = Modifier.height(8.dp))
                CustomInputField(
                    value = electricityPrice,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            electricityPrice = input
                        }
                    },
                    placeholder = "",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                LabelText(text = "Giá tiền nước")
                Spacer(modifier = Modifier.height(8.dp))
                CustomInputField(
                    value = waterPrice,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            waterPrice = input
                        }
                    },
                    placeholder = "",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                LabelText(text = "Giá tiền mạng")
                Spacer(modifier = Modifier.height(8.dp))
                CustomInputField(
                    value = internetPrice,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            internetPrice = input
                        }
                    },
                    placeholder = "",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                LabelText(text = "Giá tiền phòng")
                Spacer(modifier = Modifier.height(8.dp))
                CustomInputField(
                    value = roomPrice,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            roomPrice = input
                        }
                    },
                    placeholder = "",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Nút thêm hợp đồng
        Button(
            onClick = { /* Xử lý khi bấm thêm hợp đồng */ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Thêm hợp đồng mới",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun LabelText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}

@Composable
fun DateInputFields() {
    val context = LocalContext.current

    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    // Hàm để hiển thị DatePickerDialog
    fun showDatePicker(initialDate: Calendar, onDateSelected: (String) -> Unit) {
        android.app.DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
                onDateSelected(selectedDate)
            },
            initialDate.get(Calendar.YEAR),
            initialDate.get(Calendar.MONTH),
            initialDate.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        // Trường nhập ngày vào ở
        DateInputField(
            label = "Ngày vào ở",
            date = startDate,
            onDateClick = {
                val calendar = Calendar.getInstance()
                showDatePicker(calendar) { date ->
                    startDate = date
                }
            },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        // Trường nhập ngày kết thúc
        DateInputField(
            label = "Ngày kết thúc",
            date = endDate,
            onDateClick = {
                val calendar = Calendar.getInstance()
                showDatePicker(calendar) { date ->
                    endDate = date
                }
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            if (placeholder.isNotEmpty()) {
                Text(text = placeholder, color = Color.Gray)
            }
        },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent, // Hide the focused indicator
            unfocusedIndicatorColor = Color.Transparent, // Hide the unfocused indicator
            disabledIndicatorColor = Color.Transparent, // Hide the disabled indicator
            focusedContainerColor = Color(0xFFEEEEEE), // Set the focused background color
            unfocusedContainerColor = Color(0xFFEEEEEE), // Set the unfocused background color
            disabledContainerColor = Color(0xFFEEEEEE) // Set the disabled background color
        )
    )
}

@Composable
fun DateInputField(
    label: String,
    date: String,
    onDateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .clickable { onDateClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = date.ifEmpty { label },
                color = if (date.isEmpty()) Color.Gray else Color.Black,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.CalendarToday,
                contentDescription = "Chọn ngày",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CreateContractTopBar(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color(0xFFF5F5F5) // Màu nền trắng đục
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút Back
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .border(1.dp, Color.Gray, shape = CircleShape)
                    .background(Color.White, shape = CircleShape)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Tiêu đề và phụ đề
            Column {
                Text(
                    text = "Lập hợp đồng mới",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Phòng 1 - #104160",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
