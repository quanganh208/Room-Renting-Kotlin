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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.itptit.roomrenting.presentation.navgraph.Route

@Composable
fun QLTaiSan(roomId: String, nameRoom: String, navController: NavController) {
    Box(
        modifier = Modifier
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
                    Text(text = "Phòng $nameRoom")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .border(BorderStroke(1.dp, Color(0xffecf0f3)))
            )

            LazyTaiSan()
        }

        FloatingActionButton(
            onClick = {
                navController.navigate("${Route.AddAssetScreen.route}/$roomId/$nameRoom")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xff019e47)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun LazyTaiSan() {

    val taisans = listOf(
        Taisan(
            tenTaiSan = "Tủ lạnh",
            imageUrl = "https://cdn.tgdd.vn/Products/Images/1943/326891/tu-lanh-aqua-inverter-358-lit-aqr-t410fa-wgb-1-700x467.jpg",
        ),
        Taisan(
            tenTaiSan = "Máy giặt",
            imageUrl = "https://cdn.tgdd.vn/Products/Images/1943/326891/tu-lanh-aqua-inverter-358-lit-aqr-t410fa-wgb-1-700x467.jpg",
        ),
        Taisan(
            tenTaiSan = "Điều hòa",
            imageUrl = "https://cdn.tgdd.vn/Products/Images/1943/326891/tu-lanh-aqua-inverter-358-lit-aqr-t410fa-wgb-1-700x467.jpg",
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
                imageUrl = taisan.imageUrl
            )
        }
    }

}