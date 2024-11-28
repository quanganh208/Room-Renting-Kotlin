package com.itptit.roomrenting.presentation.other

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.itptit.roomrenting.R

@Composable
fun InvoiceScreen(navController: NavController) {

    val isAdvancedSearchVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(WindowInsets.safeDrawing.asPaddingValues())
    ){
        Column {
            Row(modifier = Modifier.padding(10.dp).background(Color.White),
                verticalAlignment = Alignment.CenterVertically){
                IconButton(onClick = {
                    navController.popBackStack()
                },
                    modifier = Modifier
                        .size(35.dp)
                        .background(color = Color(0xffeeeeee),
                            shape = CircleShape
                        )
                        .border(
                            BorderStroke(1.dp,
                            Color(0xffd8d8d8)
                            ),
                            shape = CircleShape
                        )) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        tint = Color.Unspecified,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column {
                    Text(text = "Tất cả hóa đơn", fontWeight = FontWeight.Bold)
                    Text(text = "Nhà trọ Đom Đóm")
                }
            }

            Box(modifier = Modifier.fillMaxWidth().height(1.dp).border(
                BorderStroke(1.dp,
                    Color(0xffd8d8d8)
                )
            ))

            Box(modifier = Modifier.fillMaxSize()
                .background(color = Color(0xffebeff2))
                .padding(top = 10.dp)) {

                Column(modifier = Modifier.background(Color.White)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box {
                            Row(modifier = Modifier.padding(10.dp)) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_align_horizontal_left_24),
                                    contentDescription = "",
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "Lọc theo")
                            }
                        }
                        TextButton(onClick = {
                            isAdvancedSearchVisible.value = !isAdvancedSearchVisible.value
                        }) {
                            Text(
                                text = "Tìm nâng cao",
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )

                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        chose(
                            "Tháng lập hóa đơn",
                            "Chọn tháng",
                            painterResource(id = R.drawable.baseline_calendar_today_24)
                        )
                        chose(
                            "Trạng thái hóa đơn",
                            "Chọn giá trị",
                            painterResource(id = R.drawable.baseline_keyboard_arrow_down_24)
                        )
                    }

                    val tmp = remember { mutableStateOf("") }

                    if(isAdvancedSearchVisible.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(Color.White)
                                .border(
                                    BorderStroke(1.dp, Color(0xffe3e3e3)),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextField(
                                value = tmp.value,
                                onValueChange = { newText -> tmp.value = newText },
                                placeholder = {
                                    Text(text = "Nhập phòng để tìm...")
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp),
                                singleLine = true, // Giới hạn chỉ 1 dòng nhập
                                shape = RoundedCornerShape(8.dp),
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White
                                )
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = "Calendar Icon",
                                tint = Color.Gray,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {}
                            )
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun chose(string1: String, string2: String, painter: Painter){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .border(BorderStroke(1.dp,
                Color(0xffc8c8c8)),
                shape = RoundedCornerShape(10.dp)
            )){

        TextButton(onClick = {}) {
            Column {
                Text(text = string1,
                    color = Color.Black,
                    fontWeight = FontWeight.W400)
                Text(text = string2,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold)
            }
        }

        Icon(
            painter = painter,
            contentDescription = "",
            tint = Color.Unspecified
        )
    }
}