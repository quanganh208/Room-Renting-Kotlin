package com.itptit.roomrenting.presentation.other

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.itptit.roomrenting.R

@Composable
fun ContractScreen(navController: NavController, houseName: String) {

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Column {
            Row(
                modifier = Modifier.padding(10.dp).background(Color.White).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {navController.popBackStack()},
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
                    Text(text = "Danh sách hợp đồng", fontWeight = FontWeight.Bold)
                    Text(text = "Nhà trọ $houseName")
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth().height(1.dp)
                .border(BorderStroke(1.dp, Color(0xffd5d5d5))))


            Box(modifier = Modifier.background(Color(0xffebeff2))) {

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .background(Color(0xffebeff2)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Chose(
                        "Chọn phòng",
                        "Chọn giá trị",
                        painterResource(id = R.drawable.baseline_keyboard_arrow_down_24)
                    )
                    Chose(
                        "Trạng thái hợp đồng",
                        "Chọn giá trị",
                        painterResource(id = R.drawable.baseline_keyboard_arrow_down_24)
                    )
                }
            }
        }
    }
}


@Composable
fun Chose(string1: String, string2: String, painter: Painter){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .border(BorderStroke(1.dp,
                Color(0xffc8c8c8)),
                shape = RoundedCornerShape(10.dp)
            )){

        TextButton(onClick = {}) {
            Column {
                Text(
                    modifier = Modifier.width(120.dp),
                    text = string1,
                    color = Color.Black,
                    fontWeight = FontWeight.W400,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
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