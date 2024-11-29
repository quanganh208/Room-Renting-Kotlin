package com.itptit.roomrenting.presentation.service

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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itptit.roomrenting.R


@Composable
fun Dvlist() {
    // Danh sách dữ liệu dịch vụ
    val dvlists = listOf(
        DichVuItem(
            tenDv = "Tiền điện",
            donvi = "KWh",
            price = 1.700,
            phuongthuc = "Theo đồng hồ",
            painter = painterResource(id = R.drawable.baseline_bolt_24)
        ),
        DichVuItem(
            tenDv = "Tiền nước",
            donvi = "m³",
            price = 15.000,
            phuongthuc = "Theo đồng hồ",
            painter = painterResource(id = R.drawable.baseline_invert_colors_24)
        ),
        DichVuItem(
            tenDv = "Dịch vụ khác",
            donvi = "Lần",
            price = 1000000.000,
            phuongthuc = "Thỏa thuận",
            painter = painterResource(id = R.drawable.baseline_loyalty_24)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffebeff2))
    ) {
        items(dvlists.size) { index ->
            val dichVu = dvlists[index]
            DichVu(
                ten = dichVu.tenDv,
                donvi = dichVu.donvi,
                gia = dichVu.price,
                phuongThuc = dichVu.phuongthuc,
                painter = dichVu.painter
            )
        }
    }
}


@Composable
fun ServiceScreen(
    navigaToThemDV:() -> Unit,
    onBack: () -> Unit,
    houseName: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffebeff2))
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(color = Color.White)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBack() },
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color(0xffeeeeee))
                        .border(BorderStroke(1.dp, Color.Black), shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        tint = Color.Unspecified,
                        contentDescription = "baseline_arrow_back_24",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = "Cài đặt dịch vụ",
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp
                    )
                    Text(text = "Nhà trọ $houseName", fontSize = 15.sp)
                }
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xffcacaca))
            )

            Dvlist()

        }

        FloatingActionButton(
            onClick = navigaToThemDV,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .systemBarsPadding(),
            containerColor = Color(0xff019e47)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

data class DichVuItem(
    val tenDv: String,
    val donvi: String,
    val price: Double,
    val phuongthuc: String,
    val painter: Painter
)


@Composable
fun DichVu(ten: String, donvi: String, gia: Double, phuongThuc: String, painter: Painter){
    Box()
    {
        Button(
            modifier = Modifier
                .padding(8.dp)
                .background(color = Color(0xfffefefe), shape = RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(130.dp)
                .border(BorderStroke(1.dp, color = Color(0xffebeff2)), shape = RoundedCornerShape(16.dp)),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xfffefefe)
            )
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(color = Color(0xffeeeeee), shape = CircleShape)
                    ) {
                        Icon(
                            painter = painter,
                            tint = Color.Unspecified,
                            contentDescription = "baseline_bolt_24",
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Column() {

                        Text(text = ten, fontSize = 15.sp, color = Color(0xff0c0c0c))

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(text = phuongThuc, fontSize = 15.sp, color = Color(0xff6e6e6e))

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(text = "$gia Đồng/$donvi", fontSize = 20.sp, color = Color(0xff030303), fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(5.dp))

                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color(0xffeeeeee)) // Màu nền của Row
                                .padding(horizontal = 4.dp, vertical = 2.dp), // Thêm khoảng cách bên trong Row
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(color = Color(0xff7cc82f), shape = CircleShape)
                            ) {
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(text = "Áp dụng 1 số phòng", fontSize = 10.sp, color = Color(0xff060606))
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(Color(0xffcdcdcd)),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_horizontal_rule_24),
                            contentDescription = "baseline_horizontal_rule_24",
                            tint = Color(0xffc24436),
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }


            }
        }
    }
}