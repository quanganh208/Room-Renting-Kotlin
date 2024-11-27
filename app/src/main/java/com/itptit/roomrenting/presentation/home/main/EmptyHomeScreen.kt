package com.itptit.roomrenting.presentation.home.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.navgraph.Route

@Composable
fun EmptyHomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateTopPadding()
            )
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .size(200.dp)
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(
                                1.dp,
                                color = Color(
                                    0xffd5d6d4
                                )
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(Color(0xfff1f7ed)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Đầu tiên hãy tạo nhà đẻ quản lý. Bạn có thể quản lý không giới hạn" +
                                " số tòa nhà & số phòng/giường/căn hộ",
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Bắt đầu tạo phòng từ file excel của bạn",
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )

                    Box(modifier = Modifier.padding(8.dp)) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(
                                        1.dp,
                                        color = Color(
                                            0xffd5d6d4
                                        )
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .background(
                                    Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(10.dp),
                        ) {

                            Note(
                                "Truy cập vào phiên bản máy tính",
                                "Truy cập vào website luongtiendat.me -> đăng nhập tài khoản",
                                painterResource(id = R.drawable.baseline_language_24)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .border(
                                        BorderStroke(
                                            1.dp,
                                            color = Color(
                                                0xffd5d6d4
                                            )
                                        )
                                    )
                            )

                            Note(
                                "Tải file excel mẫu",
                                "Tải file mẫu copy file excel của bạn để hoàn thành",
                                painterResource(id = R.drawable.baseline_download_24)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .border(
                                        BorderStroke(
                                            1.dp,
                                            color = Color(
                                                0xffd5d6d4
                                            )
                                        )
                                    )
                            )

                            Note(
                                "Nhập dữ liệu",
                                "Tải lại file bạn hoàn thành để nhập dữ liệu",
                                painterResource(id = R.drawable.baseline_upload_24)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .border(
                                        BorderStroke(
                                            1.dp,
                                            color = Color(
                                                0xffd5d6d4
                                            )
                                        )
                                    )
                            )


                            Note(
                                "Hỗ trợ",
                                "Liên hệ hotline khi cần hỗ trợ. 0378 775 109",
                                painterResource(id = R.drawable.baseline_phone_24)
                            )
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bắt đầu với tạo nhà thủ công",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Button(
                        onClick = { navController.navigate(Route.RentalHouseScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                0xff009e47
                            )
                        )
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Tạo nhà cho thuê",
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Note(string1: String, string2: String, painter: Painter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(color = Color.Black, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                tint = Color.White,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(text = string1, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(text = string2, fontSize = 10.sp)
        }
    }
}