package com.itptit.roomrenting.presentation.room.asset

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.itptit.roomrenting.R
import com.itptit.roomrenting.domain.model.asset.Data
import com.itptit.roomrenting.presentation.common.FullScreenLoadingModal
import com.itptit.roomrenting.presentation.navgraph.Route

@Composable
fun QLTaiSan(
    roomId: String,
    nameRoom: String,
    navController: NavController,
    viewModel: AssetRoomViewModel
) {
    val assets by viewModel.assets.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(roomId) {
        viewModel.getAllAssets(roomId)
    }


    FullScreenLoadingModal(isVisible = isLoading)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
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
                    Text(text = "Danh sách tài sản", fontWeight = FontWeight.Bold)
                    Text(text = "Phòng $nameRoom")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .border(BorderStroke(1.dp, Color(0xffecf0f3)))
            )

            LazyTaiSan(
                taisans = assets,
                navController = navController,
                roomId = roomId,
                nameRoom = nameRoom,
                viewModel = viewModel
            )
        }

        FloatingActionButton(
            onClick = {
                navController.navigate("${Route.AddAssetScreen.route}/$roomId/$nameRoom/0")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xff019e47)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun LazyTaiSan(
    taisans: List<Data> = listOf(),
    navController: NavController,
    roomId: String,
    nameRoom: String,
    viewModel: AssetRoomViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(taisans.size) { index ->
            val taisan = taisans[index]
            TaiSan(
                tenTaiSan = taisan.name,
                imageUrl = taisan.imageUrl,
                onDelete = {
                    viewModel.deleteAsset(roomId, taisan.id.toString())
                },
                onEdit = {
                    navController.navigate("${Route.AddAssetScreen.route}/$roomId/$nameRoom/${taisan.id}")
                },
            )
        }
    }
}