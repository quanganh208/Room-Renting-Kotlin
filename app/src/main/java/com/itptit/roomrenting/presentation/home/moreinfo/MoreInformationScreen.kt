package com.itptit.roomrenting.presentation.home.moreinfo

import android.content.Context
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
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.itptit.roomrenting.domain.model.auth.login.Data
import com.itptit.roomrenting.util.Constants

@Composable
fun MoreInformationScreen(onLogoutSuccess: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 16.dp)
        ) {
            // Phần Header
            HeaderSection(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Phần thông tin cuộn
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(vertical = 16.dp)
            ) {
                val items = listOf(
                    ItemData(
                        title = "Chia sẻ APP khách thuê",
                        icon = Icons.Default.Share,
                        description = "Khách kết nối với bạn, nhận hoá đơn tự động & nhiều tiện ích khách"
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

                LogoutSection(onLogoutSuccess = onLogoutSuccess)
            }
        }
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    @Composable
    fun getName(): String? {
        val context = LocalContext.current
        val sharedPreferences =
            context.getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE)
        val gson = Gson()
        val dataJson = sharedPreferences.getString(Constants.USER_DATA, null)
        return gson.fromJson(dataJson, Data::class.java)?.user?.fullName
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
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
                        text = "Xin chào! ${getName()}",
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
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Forward",
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Dòng mã khách hàng và nút sao chép
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
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
            .padding(vertical = 12.dp)
            .padding(horizontal = 8.dp),
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
fun LogoutSection(onLogoutSuccess: () -> Unit) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 16.dp)
            .clickable {
                val sharedPreferences = context.getSharedPreferences(
                    Constants.LOGIN_PREFS,
                    Context.MODE_PRIVATE
                )
                sharedPreferences
                    .edit()
                    .apply {
                        remove(Constants.USER_DATA)
                        apply()
                    }
                onLogoutSuccess()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
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