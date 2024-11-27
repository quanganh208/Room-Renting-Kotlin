package com.itptit.roomrenting.presentation.home.addcontract

import androidx.navigation.NavController
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itptit.roomrenting.R
import com.itptit.roomrenting.domain.model.FileInfo

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
fun AddContractScreen(navController: NavController, viewModel: AddContractViewModel) {
    var fileInfo by remember { mutableStateOf<FileInfo?>(null) }
    val uploadState by viewModel.uploadState.collectAsState()

    Scaffold(
        topBar = {
            AddContractTopBar(navController = navController)
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Tên hợp đồng
            InputField(title = "Tên hợp đồng*", hint = "Ví dụ: Hợp đồng thuê nhà")

            Spacer(modifier = Modifier.height(16.dp))

            // Upload file section
            FileUploadSection(onFileUploaded = { fileInfo = it })

            // Display file info
            fileInfo?.let { fileInfo ->
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start, // Canh trái các thành phần
                        modifier = Modifier
                            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                            .padding(12.dp) // Padding đồng đều cho toàn bộ Row
                            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .fillMaxWidth() // Để chiếm đủ chiều rộng
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_insert_drive_file_24),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp), // Tăng kích thước icon để phù hợp hơn
                            tint = Color.Unspecified // Dùng màu gốc của icon
                        )

                        Spacer(modifier = Modifier.width(12.dp)) // Tăng khoảng cách giữa icon và text

                        Column {
                            Text(
                                text = fileInfo.fileName,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                maxLines = 1 // Giới hạn chỉ hiển thị 1 dòng nếu tên file dài
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

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị trạng thái tải file
            when (uploadState) {
                is AddContractViewModel.UploadState.Loading -> {
                    Text(text = "Đang tải file lên...", color = Color.Gray)
                }
                is AddContractViewModel.UploadState.Success -> {
                    Text(
                        text = "Tải lên thành công: ${(uploadState as AddContractViewModel.UploadState.Success).downloadUrl}",
                        color = Color.Green
                    )
                }
                is AddContractViewModel.UploadState.Error -> {
                    Text(
                        text = "Lỗi: ${(uploadState as AddContractViewModel.UploadState.Error).message}",
                        color = Color.Red
                    )
                }
                else -> {}
            }

            Spacer(modifier = Modifier.weight(1f)) // Đẩy các nút xuống cuối màn hình

            // Nút Lưu và Đóng
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Xử lý đóng */ },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = "Đóng", color = Color.Black)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { viewModel.uploadFileToFirebase(fileInfo) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = "Lưu", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun FileUploadSection(onFileUploaded: (FileInfo) -> Unit) {
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf<String?>(null) } // Lưu thông báo lỗi

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                val contentResolver = context.contentResolver
                val mimeType = contentResolver.getType(uri)

                // Các định dạng file được hỗ trợ
                val supportedTypes = listOf(
                    "application/pdf",
                    "application/msword",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                )

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
                        fileSize = 87.59, // Replace with actual size calculation
                        uri = uri
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
fun AddContractTopBar(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color(0xFFF5F5F5) // Màu trắng đục
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
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Tiêu đề và phụ đề
            Column {
                Text(
                    text = "Thêm hợp đồng",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold // Đậm cho tiêu đề
                )
                Text(
                    text = "Nhà trọ Đức Sơn",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium // Đậm hơn một chút so với mặc định
                )
            }

        }
    }
}