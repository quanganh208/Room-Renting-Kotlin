package com.itptit.roomrenting.presentation.room

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itptit.roomrenting.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoaDon(hoaDon: Hoadon) {
    // biến check
    val showDialog = remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .border(BorderStroke(1.dp, Color(0xffeeeeee)), shape = RoundedCornerShape(16.dp))
        .fillMaxWidth()
        .background(Color.White, shape = RoundedCornerShape(16.dp))
        .clickable {
            showDialog.value = true
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(55.dp)
                        .border(
                            BorderStroke(1.dp, Color(0xffa2a2a2)),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_receipt_24),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = "Hóa đơn " + "${hoaDon.id}", fontWeight = FontWeight.Bold)

            }

            Box(
                modifier = Modifier
                    .size(45.dp)
                    .background(Color(0xffeeeeee), shape = CircleShape)
            ) {

                val sheetState = rememberModalBottomSheetState()
                val coroutineScope = rememberCoroutineScope()

                val isSheetOpen = remember { mutableStateOf(false) }
                IconButton(onClick = {
                    coroutineScope.launch {
                        sheetState.show()
                    }
                }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "",
                        modifier = Modifier.size(35.dp)
                    )
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
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .background(Color.White, shape = RoundedCornerShape(16.dp)),
                        ) {
                            Row() {
                                TextButton(onClick = {}) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .background(Color(0xffe3f5e3), shape = CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_edit_square_24),
                                            contentDescription = "",
                                            tint = Color(0xff069850)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(
                                        text = "Cập nhật ngày thanh toán",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .border(BorderStroke(1.dp, Color.Gray))
                            )

                            Row() {
                                TextButton(onClick = {}) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .background(Color(0xfff6e8e8), shape = CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_delete_24),
                                            contentDescription = "",
                                            tint = Color(0xffd84136)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(
                                        text = "Xóa Hóa Đơn",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                        }
                    }
                }

            }
        }

    }

    //Chi tiet Hoa Don
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Chi tiết hóa đơn") },
            text = {
                ChitietHD(hoaDon)
            },
            confirmButton = {
                Button(
                    onClick = { showDialog.value = false }
                ) {
                    Text("OK")
                }
            }
        )
    }

}


data class Hoadon(
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val id: Int,
    val totalPrice: Int? = null,
    val electricityPrice: Int,
    val waterPrice: Int,
    val roomPrice: Int,
    val internetPrice: Int,
    val generalPrice: Int,
    val dueDate: String? = null,
    val paymentDate: String? = null,
    val electricityNum: Int,
    val waterNum: Int
)

@Composable
fun ListHD() {
    val lists = listOf(
        Hoadon(
            createdAt = "2021-10-10T10:10:10",
            updatedAt = null,
            id = 1,
            totalPrice = 5000000,
            electricityPrice = 1000000,
            waterPrice = 500000,
            roomPrice = 3000000,
            internetPrice = 200000,
            generalPrice = 300000,
            dueDate = null,
            paymentDate = null,
            electricityNum = 100,
            waterNum = 50
        ),
        Hoadon(
            createdAt = null,
            updatedAt = null,
            id = 2,
            totalPrice = 4500000,
            electricityPrice = 800000,
            waterPrice = 400000,
            roomPrice = 3000000,
            internetPrice = 200000,
            generalPrice = 300000,
            dueDate = null,
            paymentDate = null,
            electricityNum = 90,
            waterNum = 45
        )
    )

    var selectedHoaDon = remember { mutableStateOf<Hoadon?>(null) }

    if (selectedHoaDon.value != null) {
        ChitietHD(hoaDon = selectedHoaDon.value!!)
    } else {
        LazyColumn {
            items(lists.size) { index ->
                val hoaDon = lists[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { selectedHoaDon.value = hoaDon }
                ) {
                    HoaDon(hoaDon)
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(text = "ID hóa đơn: ${hoaDon.id}")
//                        Text(text = "Tổng giá: ${hoaDon.totalPrice ?: "Chưa có"} VND")
//                        Text(text = "Hạn thanh toán: ${hoaDon.dueDate}")
//                    }
                }
            }
        }
    }

}


@Composable
fun ChitietHD(hoaDon: Hoadon) {
    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                // Sử dụng hàm chung cho các dòng thông tin có đường kẻ
                InfoRowWithBorder(label = "ID hóa đơn", value = hoaDon.id.toString())
                InfoRowWithBorder(label = "Ngày tạo", value = hoaDon.createdAt.toString())
                InfoRowWithBorder(label = "Ngày cập nhật", value = hoaDon.updatedAt.toString())
                InfoRowWithBorder(label = "Giá điện", value = "${hoaDon.electricityPrice} VND")
                InfoRowWithBorder(label = "Giá nước", value = "${hoaDon.waterPrice} VND")
                InfoRowWithBorder(label = "Tiền phòng", value = "${hoaDon.roomPrice} VND")
                InfoRowWithBorder(
                    label = "Tổng giá",
                    value = "${hoaDon.totalPrice ?: "Chưa có"} VND"
                )
                InfoRowWithBorder(label = "Hạn thanh toán", value = hoaDon.dueDate.toString())

                // Trạng thái thanh toán
                if (hoaDon.paymentDate != null) {
                    InfoRowWithBorder(
                        label = "Ngày thanh toán",
                        value = hoaDon.paymentDate.toString()
                    )
                } else {
                    Text(text = "Chưa thanh toán", color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun InfoRowWithBorder(label: String, value: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$label:", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .border(BorderStroke(1.dp, color = Color.Gray))
        )
    }
}
