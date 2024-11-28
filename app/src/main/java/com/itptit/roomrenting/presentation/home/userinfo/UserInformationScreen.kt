package com.itptit.roomrenting.presentation.home.userinfo

import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserInformationScreen(navController: NavController) {
    Scaffold(
        topBar = { MoreInformationTopBar(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Avatar và thông tin cơ bản
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4CAF50)) // Màu xanh lá cây
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Avatar",
                        tint = Color.White,
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Mã tài khoản khách hàng
                UserInfoRow(
                    label = "Mã tài khoản khách hàng",
                    value = "#2020W00008518",
                    valueColor = Color(0xFF2E7D32), // Màu xanh lá đậm
                    isUnderlined = true
                )

                // Trạng thái tài khoản
                UserStatusRow(label = "Trạng thái tài khoản", status = "Đã xác minh")

                Spacer(modifier = Modifier.height(16.dp))

                // Thông tin cá nhân
                UserInfoRow(label = "Tên", value = "Sonktx")
                UserInfoRow(label = "Số điện thoại", value = "0374-829-059")
                UserInfoRow(label = "Email", value = "không có")
                UserInfoRow(label = "Ngày tham gia", value = "20:42:31, 14/11/2024")
                UserInfoRow(label = "Đăng nhập lần đầu tiên", value = "không có")

                Spacer(modifier = Modifier.height(16.dp))

                // Nút đăng xuất
                LogoutButton()

            }
        }
    )
}

// Nút đăng xuất tài khoản
@Composable
fun LogoutButton() {
    Button(
        onClick = { /* Xử lý đăng xuất */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5)), // Màu nền xám nhạt
        contentPadding = PaddingValues(16.dp) // Thêm khoảng cách cho văn bản và icon
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp, // Icon mũi tên
            contentDescription = "Đăng xuất",
            modifier = Modifier.size(20.dp),
            tint = Color.Black // Màu của icon
        )
        Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa icon và văn bản
        Text(
            text = "Đăng xuất tài khoản",
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Hàng hiển thị trạng thái tài khoản
@Composable
fun UserStatusRow(label: String, status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier
                .background(Color(0xFFF5F5F5), shape = CircleShape) // Nền xám nhạt
                .padding(horizontal = 8.dp, vertical = 4.dp), // Padding bên trong
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4CAF50)) // Chấm xanh
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = status,
                color = Color.Black,
                fontSize = 12.sp
            )
        }
    }
}

// Hàng hiển thị thông tin chung
@Composable
fun UserInfoRow(
    label: String,
    value: String,
    valueColor: Color = Color.Black,
    isUnderlined: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Text(
            text = value,
            color = valueColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            style = if (isUnderlined) TextStyle(textDecoration = TextDecoration.Underline) else TextStyle.Default
        )
    }
}




@Composable
fun MoreInformationTopBar(navController: NavController) {
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
                    .border(1.dp, Color.Gray, shape = CircleShape) // Viền xung quanh nút
                    .background(Color.White, shape = CircleShape) // Nền trắng cho nút
                    .clickable { navController.popBackStack() }, // Quay lại màn hình trước
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black // Màu icon là đen
                )
            }

            Spacer(modifier = Modifier.width(12.dp)) // Khoảng cách giữa nút Back và tiêu đề

            // Tiêu đề và phụ đề
            Column {
                Text(
                    text = "Thêm", // Tiêu đề sửa lại theo yêu cầu
                    color = Color.Black, // Màu chữ đen
                    fontSize = 18.sp, // Kích thước chữ tiêu đề
                    fontWeight = FontWeight.Bold // Đậm cho tiêu đề
                )
                Text(
                    text = "Cá nhân", // Phụ đề
                    color = Color.Gray, // Màu chữ xám
                    fontSize = 14.sp, // Kích thước chữ phụ đề nhỏ hơn
                    fontWeight = FontWeight.Medium // Đậm nhẹ cho phụ đề
                )
            }
        }
    }
}
