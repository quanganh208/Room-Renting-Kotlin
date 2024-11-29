package com.itptit.roomrenting.presentation.room.asset

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.itptit.roomrenting.R

@Composable
fun QLTaiSan(roomId: String, navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .size(35.dp)
                        .background(
                            color = Color(0xffeeeeee),
                            shape = CircleShape
                        )
                        .border(
                            BorderStroke(
                                1.dp,
                                Color(0xffd8d8d8)
                            ),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        tint = Color.Unspecified,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column {
                    Text(text = "Danh sách tài sản", fontWeight = FontWeight.Bold)
                    Text(text = "Nhà trọ Đom Đóm")
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .border(BorderStroke(1.dp, Color(0xffecf0f3)))
            )

            LazyTaiSan()
        }

        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xff019e47)
        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.baseline_add_24),
//                tint = Color.White,
//                contentDescription = "",
//                modifier = Modifier.size(40.dp)
//            )
        }
    }
}

@Composable
fun LazyTaiSan() {

    val taisans = listOf(
        Taisan(
            tenTaiSan = "Tủ lạnh",
            gia = 1000000.0,
            soluong = "1",
            DonVi = "Cái/Chiếc",
//            painter = painterResource(id = R.drawable.baseline_panorama_vertical_select_24)
        ),
        Taisan(
            tenTaiSan = "Máy giặt",
            gia = 7500000.0,
            soluong = "1",
            DonVi = "Cái/Chiếc",
//            painter = painterResource(id = R.drawable.baseline_panorama_vertical_select_24)
        ),
        Taisan(
            tenTaiSan = "Điều hòa",
            gia = 12000000.0,
            soluong = "2",
            DonVi = "Cái/Chiếc",
//            painter = painterResource(id = R.drawable.baseline_panorama_vertical_select_24)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(taisans.size) { index ->
            val taisan = taisans[index]
            TaiSan(
                tenTaiSan = taisan.tenTaiSan,
                gia = taisan.gia,
                soluong = taisan.soluong,
                DonVi = taisan.DonVi,
//                painter = taisan.painter
            )
        }
    }

}