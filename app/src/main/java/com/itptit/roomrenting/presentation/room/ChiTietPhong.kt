package com.itptit.roomrenting.presentation.room

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val tabs = listOf("Thông tin", "Hóa Đơn", "Lịch Sử")

        tabs.forEachIndexed { index, tab ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onTabSelected(index) }
            ) {
                Text(
                    text = tab,
                    color = if (selectedTab == index) Color.Black else Color.Gray,
                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Box(
                    modifier = Modifier
                        .height(3.dp)
                        .fillMaxWidth(0.8f)
                        .background(
                            if (selectedTab == index) Color(0xFF86BF56) else Color.Transparent
                        )
                )
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {

        TopNavigationBar(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (selectedTab) {
                0 -> thongtin("Phong 1 - #105108", "Đang trống", 1000.000, "10")
                1 -> Text(text = "Nội dung tab Hóa đơn", fontSize = 20.sp)
                2 -> Text(text = "Nội dung tab Lịch sử", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun thongtin(name: String, status: String, price: Double, max_occupants: String) {
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column {
                        Text(
                            text = "Mã kết nối phòng - Dành cho APP khách thuê",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Text(text = name, fontSize = 13.sp)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .border(BorderStroke(1.dp, Color(0xffcacaca)))
                    ) {}
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Row(
                            modifier = Modifier
                                .background(Color(0xffeeeeee), shape = RoundedCornerShape(16.dp))
                                .padding(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(Color(0xffe6610e), shape = CircleShape)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = status, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Row(
                            modifier = Modifier
                                .background(Color(0xffeeeeee), shape = RoundedCornerShape(16.dp))
                                .padding(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(Color(0xff78c641), shape = CircleShape)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Chờ kỳ thu tới",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }

        item {
            Box(modifier = Modifier.background(Color.White)) {
                Column {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        Color(0xff019e47),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "#", color = Color.White, fontSize = 25.sp)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Thông tin phòng",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = "Thông tin cơ bản phòng trọ", fontSize = 14.sp)
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(35.dp)
                                    .background(Color(0xfffefefe), shape = CircleShape)
                                    .border(
                                        BorderStroke(1.dp, Color(0xffcacaca)),
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                IconButton(onClick = {}) {
                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = "",
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                Text(text = "Sửa", fontWeight = FontWeight.Bold)
                            }
                        }

                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .border(BorderStroke(1.dp, Color(0xffcacaca)))
                    ) {}
                    GridWithEqualBoxesManual()


                }
            }
        }

        item {
            Box(modifier = Modifier
                .background(Color.White)
                .padding(10.dp)) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    Color(0xff019e47),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "#", color = Color.White, fontSize = 25.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Dịch vụ của phòng",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Phòng đang sử dụng được tính vào hóa đơn",
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .border(BorderStroke(1.dp, Color(0xffcacaca)))
                    ) {}

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Tên & giá dịch vụ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Chỉ số sử dụng",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(1.dp, Color(0xffe3e3e3)),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .border(BorderStroke(1.dp, Color(0xfffdfdfd)))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(text = "Tiền điện", fontSize = 14.sp)
                                    Text(
                                        text = "1.700 đ/1 Kwh",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                }
                                Box(
                                    modifier = Modifier.background(
                                        Color(0xfffef5ee),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                ) {
                                    Text(text = "Chưa sử dụng", fontSize = 14.sp)
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .border(BorderStroke(1.dp, Color(0xffcacaca)))
                            ) {}


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(text = "Tiền nước", fontSize = 14.sp)
                                    Text(
                                        text = "18.000 đ/1 Khối",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    )
                                }
                                Box(
                                    modifier = Modifier.background(
                                        Color(0xfffef5ee),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                ) {
                                    Text(text = "Chưa sử dụng", fontSize = 14.sp)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0xffeeeeee), shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffeeeeee)
                            )
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "Chỉnh sửa dịch vụ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }

        item {
            Box(modifier = Modifier
                .background(Color.White)
                .padding(10.dp)) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    Color(0xff019e47),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "#", color = Color.White, fontSize = 25.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Quản lý phương tiện",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Quản lý xe, phương tiện của khách thuê",
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .border(BorderStroke(1.dp, Color(0xffcacaca)))
                    ) {}

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Phòng chưa có hợp đồng",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

data class tmp(
    val kieu: String,
    val giatri: String
)

@Composable
fun GridWithEqualBoxesManual() {

    val list = listOf(
        tmp("Tên phòng", "Phòng 1"),
        tmp("Nhóm", "Tầng Triệt"),
        tmp("Giá thuê", "1.000.000đ"),
        tmp("Diện tích", "15 m2"),
        tmp("Giới tính ưu tiên", "Tất cả"),
        tmp("Ngày lập hóa đơn", "Ngày 20")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        val rows = list.chunked(2)

        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { item ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .border(
                                BorderStroke(1.dp, Color(0xffd0d0d0)),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = item.kieu,
                                fontSize = 12.sp
                            )
                            Text(
                                text = item.giatri,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                    }
                }

                if (row.size < 2) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}


@Composable
fun ChiTietPhong() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xffeeeeee), shape = CircleShape)
                        .border(BorderStroke(1.dp, Color(0xffcacaca)), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(text = "Chi Tiết phòng", fontWeight = FontWeight.Bold)
                    Text(text = "Phong 1 - #105108")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .border(BorderStroke(1.dp, Color(0xffcacaca)))
            ) {}
            Box(modifier = Modifier.background(Color(0xffebeff2))) {
                MainScreen()
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xfffd0100)
                )
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "Xóa phòng",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}