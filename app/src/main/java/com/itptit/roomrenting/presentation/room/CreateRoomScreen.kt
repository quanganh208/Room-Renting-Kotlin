package com.itptit.roomrenting.presentation.room

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.common.FullScreenLoadingModal

@Composable
fun CreateRoomScreen(
    onBack: () -> Unit,
    houseId: String,
    viewModel: CreateRoomViewModel
) {
    var name by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val result by viewModel.result.collectAsState()
    var isDialogVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(result) {
        if (result.isNotEmpty()) {
            isDialogVisible = true
        }
    }

    FullScreenLoadingModal(isVisible = viewModel.isLoading.collectAsState().value)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    focusManager.clearFocus()
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
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
                        onClick = {
                            onBack()
                        },
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .border(BorderStroke(1.dp, color = Color(0xffeeeeee)))
            ) {

            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                item {
                    Note("Thông tin cơ bản", "Tên, số người ở tối đa, mô tả...")
                }
                item {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "Tên phòng trọ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Spacer(Modifier.height(5.dp))
                        TextField(
                            value = name,
                            onValueChange = { newText -> name = newText },
                            placeholder = {
                                Text(
                                    text = "VD: 101",
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
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Số người ở tối đa",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(text = "Nhập số người", fontSize = 14.sp)
                        }

                        Spacer(Modifier.width(10.dp))

                        TextField(
                            value = capacity,
                            onValueChange = { newText -> capacity = newText },
                            placeholder = {
                                Text(
                                    text = "Số người",
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
                                    text = "người",
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
                item {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "Mô tả",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Spacer(Modifier.height(5.dp))
                        TextField(
                            value = description,
                            onValueChange = { newText -> description = newText },
                            placeholder = {
                                Text(
                                    text = "Mô tả phòng trọ",
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
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                TwoButtonsRow(
                    navigateToDSPhong = onBack,
                    viewModel = viewModel,
                    houseId = houseId,
                    name = name,
                    capacity = capacity,
                    description = description
                )
            }
        }

        if (isDialogVisible) {
            AlertDialog(
                onDismissRequest = { isDialogVisible = false },
                title = { Text("Thông báo") },
                text = { Text(result) },
                confirmButton = {
                    Button(onClick = {
                        isDialogVisible = false
                        if ("thành công" in result) {
                            onBack()
                        }
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }

}

@Composable
fun TwoButtonsRow(
    navigateToDSPhong: () -> Unit,
    viewModel: CreateRoomViewModel,
    houseId: String,
    name: String,
    capacity: String,
    description: String
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
                viewModel.createRoom(
                    houseId = houseId,
                    name = name,
                    capacity = capacity.toInt(),
                    description = description
                )
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
fun Note(string1: String, string2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = Color(0xff019e47), shape = RoundedCornerShape(12.dp))
        ) {
            Text(
                text = "#",
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color.White,
                fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = string1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = string2,
                fontSize = 14.sp
            )
        }
    }
}