package com.itptit.roomrenting.presentation.home.createcontract

import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import android.provider.OpenableColumns
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import com.itptit.roomrenting.R
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
fun LabelText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
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

@Composable
fun InputField(title: String, hint: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") } // Quản lý giá trị nhập
    var isFocused by remember { mutableStateOf(false) } // Trạng thái focus

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        // Hiển thị tiêu đề với dấu sao đỏ (nếu có)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title.replace("*", ""), // Loại bỏ dấu "*" trong tiêu đề
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            if (title.contains("*")) {
                Text(
                    text = "*",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red // Màu đỏ cho dấu sao
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách giữa tiêu đề và TextField

        // TextField với nền thay đổi khi focus
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isFocused) Color(0xFFF5F5F5) else Color.White, // Nền xám khi focus
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)) // Viền xám nhạt
                .height(56.dp) // Chiều cao TextField
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text(text = hint, color = Color.Gray) }, // Placeholder màu xám
                modifier = Modifier
                    .fillMaxSize()
                    .onFocusChanged { isFocused = it.isFocused }, // Cập nhật trạng thái focus
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Xóa màu nền mặc định khi focus
                    unfocusedContainerColor = Color.Transparent, // Xóa màu nền mặc định khi unfocus
                    cursorColor = Color.Black, // Màu con trỏ
                    focusedIndicatorColor = Color.Transparent, // Ẩn viền focus mặc định
                    unfocusedIndicatorColor = Color.Transparent // Ẩn viền unfocus mặc định
                )
            )
        }
    }
}


@Composable
fun FileUploadSection(onFileUploaded: (FileInfo) -> Unit) {
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf<String?>(null) } // Lưu thông báo lỗi

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val contentResolver = context.contentResolver
            val mimeType = contentResolver.getType(uri)

            // Các định dạng file được hỗ trợ
            val supportedTypes = listOf("application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")

            if (mimeType in supportedTypes) {
                val cursor = contentResolver.query(uri, null, null, null, null)
                val fileName = if (cursor != null && cursor.moveToFirst()) {
                    val nameIndex = cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
                    cursor.getString(nameIndex)
                } else {
                    "File không xác định"
                }
                cursor?.close()

                // Tạo đối tượng FileInfo
                val fileInfo = FileInfo(
                    fileName = fileName,
                    fileSize = 87.59 // Có thể thay đổi theo kích thước thực tế
                )
                onFileUploaded(fileInfo)
                errorMessage = null // Xóa thông báo lỗi khi file hợp lệ
            } else {
                errorMessage = "Định dạng file không được hỗ trợ"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Tải lên tập tin",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Upload button
        OutlinedButton(
            onClick = { launcher.launch("*/*") }, // Chấp nhận tất cả các loại file, lọc sau
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_cloud_upload_24),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Tải lên tập tin",
                    color = Color.Black
                )
            }
        }

        // Hiển thị thông báo lỗi nếu có
        errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                color = Color.Red,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}


@Composable
fun CreateContractContent() {
    var fileInfo by remember { mutableStateOf<FileInfo?>(null) }
    // Declare state variables for input fields
    var contractName by remember { mutableStateOf("") }
    var totalMembers by remember { mutableStateOf("") }
    var customerName by remember { mutableStateOf("") }
    var customerPhone by remember { mutableStateOf("") }
    var paymentDay by remember { mutableStateOf("") }
    var electricityPrice by remember { mutableStateOf("") }
    var waterPrice by remember { mutableStateOf("") }
    var initialElectricityReading by remember { mutableStateOf("") }
    var initialWaterReading by remember { mutableStateOf("") }
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

        InputField(
            title = "Tên hợp đồng*",
            hint = "Ví dụ: Hợp đồng thuê nhà",
            value = contractName,
            onValueChange = { contractName = it }
        )
        Spacer(modifier = Modifier.height(16.dp))

        FileUploadSection(onFileUploaded = { fileInfo = it })
        // Display file info
        fileInfo?.let { fileInfo ->
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_insert_drive_file_24),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = fileInfo.fileName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${fileInfo.fileSize} KB",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
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
        InputField(
            title = "Tổng số thành viên*",
            hint = "",
            value = totalMembers,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    totalMembers = input
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tên khách* và SĐT khách (ZALO)*
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputField(
                title = "Tên khách*",
                hint = "",
                value = customerName,
                onValueChange = { customerName = it },
                modifier = Modifier.weight(1f)
            )
            InputField(
                title = "SĐT khách (ZALO)*",
                hint = "",
                value = customerPhone,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        customerPhone = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Header phần thông tin thanh toán
        ContractSectionHeader(
            title = "Thông tin thanh toán",
            description = "Nhập thông tin về thanh toán các khoản phí."
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Ngày thanh toán
        InputField(
            title = "Ngày thanh toán*",
            hint = "Nhập ngày thanh toán (1-31)",
            value = paymentDay,
            onValueChange = { input ->
                if (input.all { it.isDigit() } && (input.isEmpty() || (input.toIntOrNull()
                        ?: 0) in 1..31)
                ) {
                    paymentDay = input
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Giá tiền điện* và Giá tiền nước*
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputField(
                title = "Giá tiền điện*",
                hint = "",
                value = electricityPrice,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        electricityPrice = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            InputField(
                title = "Giá tiền nước*",
                hint = "",
                value = waterPrice,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        waterPrice = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Số điện ban đầu* và Số nước ban đầu*
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputField(
                title = "Số điện ban đầu*",
                hint = "",
                value = initialElectricityReading,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        initialElectricityReading = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            InputField(
                title = "Số nước ban đầu*",
                hint = "",
                value = initialWaterReading,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        initialWaterReading = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Giá tiền mạng* và Giá tiền phòng*
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputField(
                title = "Giá tiền mạng*",
                hint = "",
                value = internetPrice,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        internetPrice = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            InputField(
                title = "Giá tiền phòng*",
                hint = "",
                value = roomPrice,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        roomPrice = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
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
fun InputField(
    title: String,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isFocused by remember { mutableStateOf(false) } // Trạng thái focus

    Column(modifier = modifier) {
        // Hiển thị tiêu đề với dấu sao đỏ (nếu có)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title.replace("*", ""), // Loại bỏ dấu "*" trong tiêu đề
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            if (title.contains("*")) {
                Text(
                    text = "*",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red // Màu đỏ cho dấu sao
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách giữa tiêu đề và TextField

        // TextField với nền thay đổi khi focus
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isFocused) Color(0xFFF5F5F5) else Color.White, // Nền xám khi focus
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)) // Viền xám nhạt
                .height(56.dp) // Chiều cao TextField
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(text = hint, color = Color.Gray) }, // Placeholder màu xám
                keyboardOptions = keyboardOptions,
                singleLine = true,
                modifier = Modifier
                    .fillMaxSize()
                    .onFocusChanged { isFocused = it.isFocused }, // Cập nhật trạng thái focus
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // Xóa màu nền mặc định khi focus
                    unfocusedContainerColor = Color.Transparent, // Xóa màu nền mặc định khi unfocus
                    cursorColor = Color.Black, // Màu con trỏ
                    focusedIndicatorColor = Color.Transparent, // Ẩn viền focus mặc định
                    unfocusedIndicatorColor = Color.Transparent // Ẩn viền unfocus mặc định
                )
            )
        }
    }
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

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DateInputField(
            title = "Ngày vào ở*",
            date = startDate,
            onDateClick = { /* ... */ },
            modifier = Modifier.weight(1f)
        )
        DateInputField(
            title = "Ngày kết thúc*",
            date = endDate,
            onDateClick = { /* ... */ },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DateInputField(
    title: String,
    date: String,
    onDateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        // Label with red asterisk if required
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title.replace("*", ""),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            if (title.contains("*")) {
                Text(
                    text = "*",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red // Red color for asterisk
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isFocused) Color(0xFFF5F5F5) else Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                .height(56.dp)
                .clickable { onDateClick() }
                .onFocusChanged { isFocused = it.isFocused },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = date.ifEmpty { "Chọn ngày" },
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
}


data class FileInfo(
    val fileName: String,
    val fileSize: Double
)

