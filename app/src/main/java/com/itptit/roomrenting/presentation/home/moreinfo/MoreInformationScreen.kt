package com.itptit.roomrenting.presentation.home.moreinfo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itptit.roomrenting.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreInformationScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp) // Áp dụng padding ngang ở cấp cao nhất
        ) {
            // Phần Header
            HeaderSection(
                modifier = Modifier.padding(vertical = 8.dp) // Chỉ padding dọc
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Phần thông tin cuộn
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(Color.White)
                    .padding(vertical = 16.dp) // Chỉ padding dọc
            ) {
                // Danh sách các mục với mũi tên điều hướng
                val items = listOf(
                    ItemData(
                        title = "Chia sẻ APP khách thuê",
                        icon = Icons.Default.Share,
                        description = "Khách kết nối với bạn, nhận hóa đơn tự động & nhiều tiện ích khách"
                    ),
                    ItemData(
                        title = "Đánh giá phần mềm",
                        icon = Icons.Default.Star,
                        description = "Đánh giá sản phẩm và đưa ra phản hồi của bạn."
                    ),
                    ItemData(
                        title = "Thông tin phần mềm",
                        icon = Icons.Default.Info,
                        description = "Chi tiết thông tin về ứng dụng."
                    ),
                    ItemData(
                        title = "Chính sách bảo mật",
                        icon = Icons.Default.Security,
                        description = "Điều khoản về quyền riêng tư và bảo mật."
                    ),
                    ItemData(
                        title = "Điều khoản sử dụng",
                        icon = Icons.Default.Description,
                        description = "Điều khoản và điều kiện sử dụng phần mềm."
                    ),
                    ItemData(
                        title = "Phiên bản phần mềm",
                        icon = Icons.Default.Layers,
                        description = "Phiên bản: 1.0.0"
                    ),
                    ItemData(
                        title = "Hệ điều hành",
                        icon = Icons.Default.Smartphone,
                        description = "Android 13"
                    )
                )

                items.forEach { item ->
                    OptionRowWithArrow(item = item)
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Phần Đăng xuất
                LogoutSection()
            }
        }
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 16.dp) // Loại bỏ padding ngang
    ) {
        // Dòng đầu tiên: Avatar và lời chào
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4CAF50)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Xin chào! Sonktx",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Chúc bạn một ngày làm việc hiệu quả!",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    // Dòng xác minh
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF4CAF50))
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Đã xác minh",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    }
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Forward",
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dòng mã khách hàng và nút sao chép
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Mã khách hàng",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "#2020W00008518",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50),
                    textDecoration = TextDecoration.Underline
                )
            }
            OutlinedButton(
                onClick = { /* Xử lý sao chép */ },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color(0xFFE8F5E9)
                ),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copy",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sao chép",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun OptionRowWithArrow(item: ItemData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Xử lý khi nhấn vào mục */ }
            .padding(vertical = 12.dp), // Chỉ padding dọc
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Phần biểu tượng và thông tin mục
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    if (item.isNew) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mới",
                            fontSize = 12.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color(0xFF4CAF50), shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Biểu tượng mũi tên hướng tới
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Forward",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFF4CAF50)
    ) {
        // Chỉ giữ lại hai mục: "Trang chủ" và "Thêm"
        val items = listOf("Trang chủ", "Thêm")
        val icons = listOf(
            Icons.Default.Home,
            Icons.Default.MoreHoriz
        )

        // Sử dụng Row để căn chỉnh hai mục với đường viền phân cách
        Row(
            modifier = Modifier
                .fillMaxWidth() // Chiếm toàn bộ chiều rộng của màn hình
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Mục đầu tiên: "Trang chủ"
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[0],
                        contentDescription = items[0]
                    )
                },
                label = { Text(text = items[0]) },
                selected = false, // Bạn nên quản lý trạng thái chọn
                onClick = { /* Xử lý khi nhấn vào */ },
                modifier = Modifier.weight(1f)
            )

            // Đường phân cách giữa hai mục
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .height(40.dp) // Chiều cao vừa đủ cho đường phân cách
                    .width(1.dp)
            )

            // Mục thứ hai: "Thêm"
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = icons[1],
                        contentDescription = items[1]
                    )
                },
                label = { Text(text = items[1]) },
                selected = false, // Bạn nên quản lý trạng thái chọn
                onClick = { /* Xử lý khi nhấn vào */ },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun LogoutSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Xử lý đăng xuất */ }
            .background(Color.White)
            .padding(vertical = 16.dp), // Chỉ padding dọc
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.ExitToApp,
            contentDescription = "Logout",
            tint = Color.Red,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Đăng xuất tài khoản",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }
}

data class ItemData(
    val title: String,
    val icon: ImageVector,
    val description: String,
    val isNew: Boolean = false
)
