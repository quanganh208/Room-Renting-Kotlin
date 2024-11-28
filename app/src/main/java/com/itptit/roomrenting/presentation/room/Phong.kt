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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itptit.roomrenting.R
import com.itptit.roomrenting.domain.model.room.Data
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Phong(room: Data) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val isSheetOpen = remember { mutableStateOf(false) }

    Button(
        onClick = {
            coroutineScope.launch {
                sheetState.show()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
//            .height(220.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(color = Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = Color.LightGray, shape = CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Color.Black,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Text(text = "Phòng ${room.name}", fontSize = 18.sp, color = Color.Black)
                }
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.show()
                        }
                    },
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xffeeeeee))
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "MoreVert",
                        tint = Color.Black
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .border(BorderStroke(1.dp, color = Color(0xffebebeb)))
            )

            Spacer(Modifier.height(10.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .border(
                        BorderStroke(1.dp, color = Color(0xffd3d3d3)),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Info, contentDescription = "Info")
                        Text(text = "Trạng thái", fontSize = 13.sp, color = Color.Black)
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier.background(
                                color = Color(0xffeeeeee),
                                shape = RoundedCornerShape(16.dp)
                            )
                        ) {
                            Row(modifier = Modifier.padding(3.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(
                                            color = if (room.isCurrentlyRented) Color(0xFF03A9F4) else Color(
                                                0xffe56307
                                            )
                                        )
                                ) { }
                                Spacer(Modifier.width(3.dp))
                                Text(
                                    text = if (room.isCurrentlyRented) "Đã cho thuê" else "Đang trống",
                                    fontSize = 13.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.W500
                                )
                            }
                        }

                        Box(
                            Modifier.background(
                                color = Color(0xffeeeeee),
                                shape = RoundedCornerShape(16.dp)
                            )
                                .padding(3.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(CircleShape)
                                        .background(color = Color(0xff84be4b))
                                ) { }
                                Spacer(Modifier.width(3.dp))
                                Text(
                                    text = "Chờ kỳ thu tới",
                                    fontSize = 13.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.W500
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Có thể ở tối đa", color = Color(0xff959595))
                    Text(text = "${room.capacity} người", fontSize = 20.sp)
                }
                Button(
                    modifier = Modifier
                        .width(115.dp)
                        .height(50.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xffe4f4ea)
                    )
                ) {
                    Text(
                        text = "Đăng tin",
                        color = Color(0xff038f48),
                        fontSize = 15.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = "baseline_arrow_forward_ios_24",
                        Modifier.size(15.dp),
                        tint = Color(0xff038f48)
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .border(BorderStroke(1.dp, color = Color(0xffebebeb)))
            )

            Row(modifier = Modifier.fillMaxWidth().height(40.dp),
                horizontalArrangement = Arrangement.Absolute.Left,
                verticalAlignment = Alignment.CenterVertically){
                Text(text = "Ghi chú:", fontSize = 12.sp, color = Color(0xff959595))
                Spacer(Modifier.width(10.dp))
                Text(text = "Chèn string", fontSize = 12.sp)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .border(BorderStroke(1.dp, color = Color(0xffebebeb)))
            )

            Row(modifier = Modifier.fillMaxWidth().height(40.dp),
                horizontalArrangement = Arrangement.Absolute.Left,
                verticalAlignment = Alignment.CenterVertically){
                Text(text = "Cập nhật gần nhất:", fontSize = 12.sp, color = Color(0xff959595))
                Spacer(Modifier.width(10.dp))
                Text(text = "Chèn string", fontSize = 12.sp )
            }


        }
    }

    if (isSheetOpen.value || sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    sheetState.hide()
                }
            },
            sheetState = sheetState
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color(0xfffefefe))
            ) {
                In4(room)
            }
        }
    }
}
