package com.itptit.roomrenting.presentation.room

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itptit.roomrenting.R

@Composable
fun CreateRoomScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(40.dp)
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
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Thêm phòng",
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .border(BorderStroke(1.dp, color = Color(0xffeeeeee)))){

        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            item {
                Note("Thông tin cơ bản", "Tên, giá thuê, diện tích, ngày lập hóa đơn")
            }
            item {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Tên phòng trọ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(text = "POO1, POO2", fontSize = 15.sp)
                    Input("Ví dụ: Phòng số 1")
                }
            }
            item {
                Column(modifier = Modifier.padding(8.dp)) {
                    Nhap("Diện tích", "Nhập diện tích phòng", "m2", "Diện tích")
                    Spacer(Modifier.height(30.dp))
                    Nhap("Giá thuê", "Giá phòng tính đơn vị theo tháng", "đ/tháng", "Giá thuê")
                    Spacer(Modifier.height(30.dp))
                    Nhap("Ngày lập hóa đơn", "Hệ thống sẽ nhắc nhở khi phòng tới ngày lập hóa đơn", "", "Ngày")
                    Spacer(Modifier.height(30.dp))
                }
            }
            item {
                Note("Dịch vụ sử dụng", "Tiền điện, nước, rác, wifi...")
            }
            item {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xfff3f3f3)
                    ),
                    contentPadding = PaddingValues() // Để xóa padding mặc định của Button
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "Create",
                            modifier = Modifier.size(18.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text = "Chỉnh sửa dịch vụ",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }

            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            TwoButtonsRow(onBack)
        }
    }

}

@Composable
fun TwoButtonsRow(
    navigateToDSPhong: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = navigateToDSPhong,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Đóng",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = {
                println("Nút + Thêm phòng được bấm!")
            },
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff019e47)),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "+ Thêm phòng",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun Nhap(string1: String, string2: String, string: String, string3: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(text = string1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp)
            Text(text = string2, fontSize = 14.sp)
        }

        Spacer(Modifier.width(10.dp))

        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            placeholder = {
                Text(
                    text = string3,
                    fontSize = 15.sp
                )
            },
            singleLine = true,
            modifier = Modifier
                .widthIn(max = 160.dp)
                .border(
                    BorderStroke(1.dp, color = Color(0xffe5e5e5)),
                    shape = RoundedCornerShape(15.dp)
                )
                .clip(RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(15.dp),
            trailingIcon = {
                Text(
                    text = string,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0xfff2f8ec))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        )
    }
}

@Composable
fun Input(string:String) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { newText -> text = newText },
        placeholder = {
            Text(
                text = string,
                fontSize = 15.sp
            )
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, color = Color(0xffe5e5e5)),
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp)),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(15.dp)
    )
}

@Composable
fun Note(string1: String, string2: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start){
        Box(modifier = Modifier
            .size(40.dp)
            .background(color = Color(0xff019e47), shape = RoundedCornerShape(12.dp))){
            Text(text = "#",
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color.White,
                fontSize = 25.sp)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = string1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold)
            Text(text = string2,
                fontSize = 14.sp)
        }
    }
}