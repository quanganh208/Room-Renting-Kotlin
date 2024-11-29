package com.itptit.roomrenting.presentation.service


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun Input(string : String) {

    val textState = remember { mutableStateOf("") }

    TextField(
        value = textState.value,
        onValueChange = { newText -> textState.value = newText },
        label = { Text(text = string) },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, color = Color(0xffe3e3e3)), shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}

@Composable
fun Chose(string: String){
    Box {

        TextButton(onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xfff2f8ec)
            )) {
            Text(text = string,
                color = Color(0xff1a8e59),
                fontSize = 13.sp)
        }
    }
}

@Composable
fun ToggleSwitchExample() {

    val isChecked = remember { mutableStateOf(false) }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Switch(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            modifier = Modifier.size(60.dp),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF4CAF50), // Màu khi bật (On)
                uncheckedThumbColor = Color(0xFF9E9E9E), // Màu khi tắt (Off)
                checkedTrackColor = Color(0xFF4CAF50).copy(alpha = 0.5f), // Màu nền khi bật
                uncheckedTrackColor = Color(0xFF9E9E9E).copy(alpha = 0.5f) // Màu nền khi tắt
            )
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddServiceScreen(
    onBack:() -> Unit,
    houseName : String
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffebeff2))
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(color = Color.White)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBack,
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Cài đặt dịch vụ",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp
                        )
                        Text(text = "Nhà trọ $houseName", fontSize = 15.sp)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Tiền dịch vụ")
                    Input("Tiền điện")
                    Text("Ví dụ đề xuất:", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Row {
                        Chose("Tiền wifi")
                        Spacer(modifier = Modifier.width(10.dp))
                        Chose("Tiền giữ xe")
                        Spacer(modifier = Modifier.width(10.dp))
                        Chose("Tiền nước (người)")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item{
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "ic_launcher_background",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(50.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Column {
                            Row(modifier = Modifier
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(modifier = Modifier
                                    .width(250.dp)) {
                                    Text(text = "Tính theo kiểu đồng hồ điện, nước ?",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp)
                                    Text(text = "Mức sử dụng của khách thuê có sự chênh lệch trước & sau",
                                        fontSize = 15.sp)
                                }

                                Spacer(modifier = Modifier.width(5.dp))

                                ToggleSwitchExample()
                            }
                            Text(text = "* Ví dụ: Dịch vụ điện, nước có sự chênh lệch số cũ và số mới",
                                color = Color(0xffd67339),
                                fontSize = 15.sp)
                        }

                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .background(Color(0xfffefefe))
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Đơn vị và giá", fontWeight = FontWeight.Bold)
                    Text(text = "Nhập thông tin đơn vị tính và giá dịch vụ")

                    Box(
                        modifier = Modifier
                            .fillMaxWidth().height(1.dp)
                            .border(BorderStroke(1.dp, color = Color(0xffd8d8d8)))
                    ) {
                    }
                    Input("Nhập Đơn vị")
                    Text(text = "Giá dịch vụ", fontWeight = FontWeight.Bold)
                    Text(text = "Ví dụ Tiền rác: 30.000đ / 1 người, bạn nhập là 30000")
                    Input("Nhập giá dịch vụ")
                }
            }

        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color(0xff019e47), shape = RoundedCornerShape(16.dp))
            .align(Alignment.BottomCenter)){
            Button(onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff019e47)
                ))
            {
                Text(text = "Thêm mới dịch vụ",
                    color = Color(0xfff3fffe),
                    fontSize = 15.sp)
            }

        }

    }
}