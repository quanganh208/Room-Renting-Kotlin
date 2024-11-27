package com.itptit.roomrenting.presentation.home.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Quản Lý") }
    var showBottomSheet by remember { mutableStateOf(false) }
    var showMenuBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { HeaderWithDropdown(onDropdownClick = { showBottomSheet = true }, onShowMenuBottomSheet = {showMenuBottomSheet = true}) },
        modifier = Modifier
            .fillMaxSize()
            .padding(top = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding())
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F4F8)) // Tổng thể màu nền
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            // Thanh tab "Quản Lý" và "Tổng Quan"
            TabSectionFullWidth(
                selectedTab = selectedTab,
                onTabSelected = { tab ->
                    selectedTab = tab // Cập nhật trạng thái khi tab được chọn
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị nội dung theo tab
            when (selectedTab) {
                "Quản Lý" -> {
                    // Nội dung cho tab Quản Lý
                }
                "Tổng Quan" -> {
                    // Nội dung cho tab Tổng Quan
                }
            }

            Column(modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState())
            ){
                // Các thành phần khác
                SectionTitleWithIconNoBackground(
                    title = "Menu quản lý nhà trọ",
                    description = "Quản lý đối tương nghiệp vụ trong nhà trọ"
                )
                FeatureItemStyled(
                    icon = Icons.Default.Home,
                    title = "Quản lý phòng",
                    description = "Theo dõi và quản lý danh sách phòng, trạng thái sử dụng, và cập nhật thông tin phòng trọ một cách dễ dàng."
                )

                FeatureItemStyled(
                    icon = Icons.AutoMirrored.Filled.List,
                    title = "Quản lý hóa đơn",
                    description = "Quản lý hóa đơn tiền thuê, điện, nước và các khoản phí khác của từng khách hàng."
                )

                FeatureItemStyled(
                    icon = Icons.Default.Info,
                    title = "Quản lý dịch vụ",
                    description = "Theo dõi và quản lý các dịch vụ bổ sung như vệ sinh, internet, và các tiện ích khác trong nhà trọ."
                )

                FeatureItemStyled(
                    icon = Icons.AutoMirrored.Filled.ArrowForward,
                    title = "Quản lý hợp đồng",
                    description = "Quản lý thông tin hợp đồng của từng khách thuê, bao gồm ngày bắt đầu, ngày kết thúc, và các điều khoản chi tiết."
                )

                FeatureItemStyled(
                    icon = Icons.Default.Search,
                    title = "Quản lý tài sản",
                    description = "Theo dõi và kiểm kê tài sản của nhà trọ, bao gồm nội thất, thiết bị, và các vật dụng khác."
                )
            }
        }

        // Hiển thị BottomSheet khi showBottomSheet = true
        if (showBottomSheet) {
            BottomSheetContent(
                onClose = { showBottomSheet = false }
            )
        }

        if (showMenuBottomSheet) {
            MenuBottomSheet(
                onClose = { showMenuBottomSheet = false }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuBottomSheet(onClose: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = onClose,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp) // Căn chỉnh khoảng cách
        ) {
            // Mục "Thêm mới tòa nhà cho thuê"
            MenuOptionItem(
                icon = Icons.Default.Add,
                title = "Thêm mới tòa nhà cho thuê",
                description = "Bạn có thể thêm nhiều nhà tài sản cho thuê để quản lý."
            )

            // Mục "Chỉnh sửa thông tin"
            MenuOptionItem(
                icon = Icons.Default.Edit,
                title = "Chỉnh sửa thông tin \"Đức Sơn\"",
                description = "Chỉnh sửa nhà trọ hiện tại. Bao gồm tên, địa chỉ..."
            )

            // Mục "Cài đặt"
            MenuOptionItem(
                icon = Icons.Default.Settings,
                title = "Cài đặt",
                description = "Cài đặt như: máy in, chức năng, tiện ích, giờ giấc, nội quy..."
            )
        }
    }
}

@Composable
fun MenuOptionItem(icon: ImageVector, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Xử lý sự kiện nhấn nếu cần */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon bên trái
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF4CAF50), // Màu xanh lá
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFDFF4E3), shape = CircleShape) // Nền xanh nhạt
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Nội dung tiêu đề và mô tả
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun HeaderWithDropdown(onDropdownClick: () -> Unit, onShowMenuBottomSheet: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50)) // Màu xanh lá
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon ngôi nhà trong ô tròn
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon",
                    tint = Color(0xFF4CAF50), // Màu xanh lá
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Văn bản "Đang quản lý Nhà trọ" và "Đức Sơn" với mũi tên
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Đang quản lý Nhà trọ",
                    color = Color.White,
                    fontSize = 14.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onDropdownClick() }
                ) {
                    Text(
                        text = "Đức Sơn",
                        color = Color.White,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Icon",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Nút menu dấu ba vạch với hình vuông bao quanh
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)) // Hình vuông trắng với góc bo nhỏ
                    .clickable { // Xử lý sự kiện nhấn
                        onShowMenuBottomSheet()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Menu Icon",
                    tint = Color(0xFF4CAF50), // Màu xanh lá
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(onClose: () -> Unit) {
    // Sử dụng ModalBottomSheet để hỗ trợ hiển thị
    ModalBottomSheet(
        onDismissRequest = onClose,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Loại bỏ padding dọc thừa phía trên
        ) {
            // Header của Bottom Sheet
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 4.dp), // Giảm khoảng cách phía trên
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Danh sách nhà đang quản lý",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Gray
                    )
                }
            }

            Text(
                text = "Có thể thêm nhiều hơn 1 nhà để quản lý",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp)) // Giảm khoảng cách giữa phần tiêu đề và danh sách

            // Danh sách nhà trọ
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Icon nhà trọ bên trái
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFFDFF4E3), shape = CircleShape), // Nền xanh nhạt
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Icon Nhà",
                                tint = Color(0xFF4CAF50), // Màu xanh lá
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Thông tin nhà trọ
                        Column(
                            modifier = Modifier.weight(0.5f) // Chiếm không gian lớn
                        ) {
                            Text(
                                text = "Nhà trọ Đức Sơn",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.Black
                            )
                            Text(
                                text = "5 phòng cho thuê - 0 tin đăng",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                            Text(
                                text = "Mỗ Lao, Hà Đông, Văn Quán, Hà Đông, Thành phố Hà Nội",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }

                        // Dấu tích xanh bên phải
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Check Icon",
                            tint = Color(0xFF4CAF50), // Màu xanh lá
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp)) // Cách khoảng nhỏ trước các nút

                    // Nút "Xóa" và "Quản lý"
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween // Cách đều 2 nút
                    ) {
                        Button(
                            onClick = { /* Xử lý xóa */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)), // Màu cam
                            modifier = Modifier.weight(1f).padding(end = 8.dp) // Căn chỉnh cân đối
                        ) {
                            Text(text = "Xóa", color = Color.White)
                        }
                        Button(
                            onClick = { /* Xử lý quản lý */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Màu xanh
                            modifier = Modifier.weight(1f).padding(start = 8.dp)
                        ) {
                            Text(text = "Quản Lý", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TabSectionFullWidth(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.White) // Nền trắng tổng thể
            .padding(horizontal = 16.dp) // Thêm padding hai bên để cắt bớt màu xanh
    ) {
        TabItemFullWidth(
            title = "Quản Lý",
            isSelected = selectedTab == "Quản Lý", // Xác định nếu tab được chọn
            icon = Icons.Default.Home,
            onClick = { onTabSelected("Quản Lý") },
            modifier = Modifier.weight(1f) // Chia đều không gian
        )
        TabItemFullWidth(
            title = "Tổng Quan",
            isSelected = selectedTab == "Tổng Quan", // Xác định nếu tab được chọn
            icon = Icons.AutoMirrored.Filled.List,
            onClick = { onTabSelected("Tổng Quan") },
            modifier = Modifier.weight(1f) // Chia đều không gian
        )
    }
}

@Composable
fun TabItemFullWidth(
    title: String,
    isSelected: Boolean,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(
                color = if (isSelected) Color(0xFF1976D2) else Color.White, // Màu xanh khi được chọn
                shape = RoundedCornerShape(0.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center // Căn giữa nội dung
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 8.dp) // Cách đều nội dung
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (isSelected) Color.White else Color.Black, // Icon màu trắng khi được chọn
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp)) // Khoảng cách giữa icon và chữ
            Text(
                text = title,
                color = if (isSelected) Color.White else Color.Black, // Văn bản màu trắng khi được chọn
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun SectionTitleWithIconNoBackground(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp), // Khoảng cách nhỏ hơn để sát với mục "Quản Lý"
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Dấu màu xanh ở đầu dòng
        Box(
            modifier = Modifier
                .size(4.dp, 24.dp)
                .background(Color(0xFF4CAF50)) // Màu xanh lá
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f) // Chiếm phần lớn không gian
        ) {
            // Tiêu đề
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )

            // Mô tả
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Icon dấu chấm hỏi
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Info Icon",
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun FeatureItemStyled(
    icon: ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon bên trái
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFDFF4E3), shape = CircleShape), // Nền màu xanh nhạt
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color(0xFF4CAF50), // Màu xanh lá
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Nội dung bên phải
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            // Icon mũi tên chỉ sang phải
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward, // Thay bằng Icon mũi tên nếu cần
                contentDescription = "Arrow Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

//        Column {
//            Button(
//                onClick = {
//                    val sharedPreferences =
//                        navController.context.getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE)
//                    with(sharedPreferences.edit()) {
//                        remove(Constants.ACCESS_TOKEN)
//                        apply()
//                    }
//                    navController.navigate(Route.LoginScreen.route) {
//                        popUpTo(Route.HomeScreen.route) { inclusive = true }
//                    }
//                }
//            ) {
//                Text(text = "Logout")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    homeViewModel.getHouses()
//                }
//            ) {
//                Text(text = "Get House")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            if (isLoading) {
//                CircularProgressIndicator()
//            } else {
//                Text(text = houses)
//            }
//        }
//    }
