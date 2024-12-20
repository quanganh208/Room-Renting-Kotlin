package com.itptit.roomrenting.presentation.home.addasset

import com.itptit.roomrenting.presentation.common.FullScreenLoadingModal
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.itptit.roomrenting.presentation.navgraph.Route

@Composable
fun InputField(
    title: String,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
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
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
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
fun AddAssetScreen(
    navController: NavController,
    viewModel: AddAssetViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    roomId: String,
    nameRoom: String,
    assetId: String
) {
    var text by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var isDialogVisible by remember { mutableStateOf(false) }
    val result by viewModel.result.collectAsState()
    val focusManager = LocalFocusManager.current

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }

    LaunchedEffect(result) {
        if (result.isNotEmpty()) {
            isDialogVisible = true
        }
    }

    FullScreenLoadingModal(isVisible = viewModel.isLoading.collectAsState().value)
    Scaffold(
        topBar = { AddAssetTopBar(navController, nameRoom, assetId) },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .clickable(
                onClick = {
                    focusManager.clearFocus()
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Input field for the asset name
            InputField(
                title = "Tên tài sản*",
                hint = "Ví dụ: Tủ lạnh",
                onValueChange = { text = it })

            Spacer(modifier = Modifier.height(16.dp))

            // Image picker section with the updated function
            ImagePickerSection(
                selectedImageUri = selectedImageUri,
                onPickImage = { launcher.launch("image/*") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
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
                    onClick = {
                        if (assetId == "0") {
                            viewModel.createAsset(roomId, selectedImageUri, text)
                        } else {
                            viewModel.updateAsset(roomId, assetId, selectedImageUri, text)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = if (assetId == "0") "+ Thêm tài sản" else "Cập nhật tài sản",
                        color = Color.White
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
                            navController.popBackStack()
                        }
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}


@Composable
fun ImagePickerSection(selectedImageUri: Uri?, onPickImage: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Chọn ảnh",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Display the selected image or show the placeholder
        Box(
            modifier = Modifier
                .size(240.dp) // Box size
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clickable { onPickImage() }, // Trigger the image picker
            contentAlignment = Alignment.Center
        ) {
            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "Ảnh đã chọn",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)), // Rounded corners for the image
                    contentScale = ContentScale.Crop // Scale the image
                )
            } else {
                Text(
                    text = "Chọn ảnh",
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun AddAssetTopBar(navController: NavController, roomName: String, assetId: String) {
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
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Tiêu đề và phụ đề
            Column {
                Text(
                    text = if (assetId == "0") "Thêm tài sản" else "Chỉnh sửa tài sản",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold // Đậm cho tiêu đề
                )
                Text(
                    text = "Phòng trọ ${roomName}",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium // Đậm hơn một chút so với mặc định
                )
            }

        }
    }
}
