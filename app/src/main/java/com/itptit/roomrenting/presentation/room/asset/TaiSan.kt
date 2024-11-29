package com.itptit.roomrenting.presentation.room.asset

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaiSan(tenTaiSan: String, imageUrl: String) {
    Box(modifier = Modifier
        .border(BorderStroke(1.dp,Color(0xffeeeeee)), shape = RoundedCornerShape(16.dp))
        .fillMaxWidth()
        .background(Color.White, shape = RoundedCornerShape(16.dp))){
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(55.dp)
                    .border(BorderStroke(1.dp, Color(0xffa2a2a2)),
                        shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp).clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))
                Text(text = tenTaiSan, fontWeight = FontWeight.Bold)
            }

            Box(modifier = Modifier
                .size(45.dp)
                .background(Color(0xffeeeeee), shape = CircleShape)) {
                val sheetState = rememberModalBottomSheetState()
                val coroutineScope = rememberCoroutineScope()

                val isSheetOpen = remember { mutableStateOf(false) }
                IconButton(onClick = {
                    coroutineScope.launch{
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
                            Row {
                                TextButton(onClick = {}) {
                                    Box(modifier = Modifier
                                        .size(40.dp)
                                        .background(Color(0xffe3f5e3), shape = CircleShape),
                                        contentAlignment = Alignment.Center) {
                                        Icon(
                                            Icons.Default.Edit,
                                            contentDescription = "",
                                            tint = Color(0xff069850)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(text = "Chỉnh sửa: $tenTaiSan",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold)
                                }
                            }

                            Box(modifier = Modifier.fillMaxWidth().height(1.dp)
                                .border(BorderStroke(1.dp,Color.Gray)))

                            Row {
                                TextButton(onClick = {}) {
                                    Box(modifier = Modifier
                                        .size(40.dp)
                                        .background(Color(0xfff6e8e8), shape = CircleShape),
                                        contentAlignment = Alignment.Center){
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "",
                                            tint = Color(0xffd84136)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(text = "Xóa: $tenTaiSan",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold)
                                }
                            }

                        }
                    }
                }

            }
        }
    }
}