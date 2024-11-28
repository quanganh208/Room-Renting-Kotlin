package com.itptit.roomrenting.presentation.room

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itptit.roomrenting.R
import com.itptit.roomrenting.domain.model.room.Data

@Composable
fun In4(room: Data) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(color = Color(0xffeeeeee)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Menu, contentDescription = "Menu",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Text(text = "Phòng ${room.name}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "Menu thao tác phòng", fontSize = 20.sp)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .border(BorderStroke(10.dp, color = Color(0xffe2e2e2)))
        ) {
        }
        Spacer(modifier = Modifier.height(10.dp))

        Box {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .border(
                        BorderStroke(1.dp, color = Color(0xffcbcbcb)),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Ktra(
                    painterResource(id = R.drawable.baseline_remove_red_eye_24),
                    "Xem chi tiết phòng"
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .border(BorderStroke(10.dp, color = Color(0xffe2e2e2)))
                ) {
                }
                Ktra(
                    painterResource(id = R.drawable.baseline_edit_24),
                    "Chỉnh sửa thông tin cơ bản"
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .border(BorderStroke(10.dp, color = Color(0xffe2e2e2)))
                ) {
                }
                Ktra(painterResource(id = R.drawable.baseline_bookmark_add_24), "Lập hợp đồng mới")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .border(BorderStroke(10.dp, color = Color(0xffe2e2e2)))
                ) {
                }
                Ktra(painterResource(id = R.drawable.baseline_edit_location_24), "Cọc giữ chỗ")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .border(BorderStroke(10.dp, color = Color(0xffe2e2e2)))
                ) {
                }
                Ktra(painterResource(id = R.drawable.baseline_settings_24), "Thiết lập dịch vụ")
            }
        }
    }
}

@Composable
fun Ktra(painter: Painter, string: String) {
    Box {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color(0xfffefefe)),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xfffefefe)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomIcon(painter)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = string, fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = "baseline_arrow_forward_ios_24",
                    tint = Color.Unspecified,
                )
            }
        }
    }
}


@Composable
fun CustomIcon(
    painter: Painter,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    size: Dp = 30.dp,
    tint: Color = Color.Black
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        tint = tint
    )
}