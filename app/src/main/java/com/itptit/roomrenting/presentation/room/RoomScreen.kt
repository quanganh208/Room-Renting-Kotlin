package com.itptit.roomrenting.presentation.room

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.common.FullScreenLoadingModal
import com.itptit.roomrenting.presentation.navgraph.Route


@Composable
fun RoomRented(viewModel: RoomViewModel, houseId: Int, navController: NavController) {
    val rooms =
        viewModel.rooms.collectAsState().value.data.filter { it.isCurrentlyRented }.toMutableList()
    Column(
        modifier = Modifier
            .background(color = Color(0xffe6f2ee))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        rooms.forEach {
            Phong(
                room = it,
                onDeleteRoom = {
                    viewModel.deleteRoom(houseId.toString(), it.id.toString())
                },
                onEditRoom = {
                    navController.navigate("${Route.CreateRoomScreen.route}/$houseId/${it.id}")
                },
                onAssetRoom = {
                    navController.navigate("${Route.AssetRoomScreen.route}/${it.id}/${it.name}")
                },
                onCreateContract = {
                    navController.navigate("${Route.CreateContractScreen.route}/${it.id}/${it.name}")
                },
                onDetailRoom = {
                    navController.navigate("${Route.DetailRoomScreen.route}/$houseId/${it.id}")
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun AllRoomRent(viewModel: RoomViewModel, houseId: Int, navController: NavController) {
    val rooms = viewModel.rooms.collectAsState().value.data.toMutableList()
    Column(
        modifier = Modifier
            .background(color = Color(0xffe6f2ee))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        rooms.forEach {
            Phong(
                room = it,
                onDeleteRoom = {
                    viewModel.deleteRoom(houseId.toString(), it.id.toString())
                },
                onEditRoom = {
                    navController.navigate("${Route.CreateRoomScreen.route}/$houseId/${it.id}")
                },
                onAssetRoom = {
                    navController.navigate("${Route.AssetRoomScreen.route}/${it.id}/${it.name}")
                },
                onCreateContract = {
                    navController.navigate("${Route.CreateContractScreen.route}/${it.id}/${it.name}")
                },
                onDetailRoom = {
                    navController.navigate("${Route.DetailRoomScreen.route}/$houseId/${it.id}")
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun TabNavigationExample(viewModel: RoomViewModel, houseId: Int, navController: NavController) {
    val tabs = listOf("Toàn bộ phòng", "Đã cho thuê")
    val selectedTab = remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = selectedTab.intValue,
                contentColor = Color.Black,
                indicator = { tabPositions ->
                    Box(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTab.intValue])
                            .background(color = Color(0xFF019E47))
                            .fillMaxWidth()
                            .height(4.dp)
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab.intValue == index,
                        onClick = { selectedTab.intValue = index },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = {
                            Text(
                                text = title,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = if (selectedTab.intValue == index) Color.Black else Color.Black
                                )
                            )
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Gray
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            when (selectedTab.intValue) {
                0 -> AllRoomRent(
                    viewModel = viewModel,
                    houseId = houseId,
                    navController = navController
                )

                1 -> RoomRented(
                    viewModel = viewModel,
                    houseId = houseId,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RoomScreen(
    navController: NavController,
    houseId: Int,
    houseName: String,
    viewModel: RoomViewModel
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val result by viewModel.result.collectAsState()
    var isDialogVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getRooms(houseId.toString())
    }

    LaunchedEffect(result) {
        if (result.isNotEmpty()) {
            isDialogVisible = true
        }
    }

    FullScreenLoadingModal(isVisible = isLoading)
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(
                    top = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateTopPadding()
                )
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
                            navController.popBackStack()
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
                    Column {
                        Text(
                            text = "Danh sách phòng",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp
                        )
                        Text(text = "Nhà trọ ${houseName}", fontSize = 15.sp)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .border(BorderStroke(1.dp, color = Color(0xffeeeeee)))
                ) {

                }

                Spacer(modifier = Modifier.height(10.dp))
                TabNavigationExample(viewModel, houseId, navController)
            }

        }

        FloatingActionButton(
            onClick = {
                navController.navigate("${Route.CreateRoomScreen.route}/$houseId/0")
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
                .systemBarsPadding(),
            containerColor = Color(0xff019e47)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }

        if (isDialogVisible) {
            AlertDialog(
                onDismissRequest = { isDialogVisible = false },
                title = { Text("Thông báo") },
                text = { Text(result) },
                confirmButton = {
                    Button(onClick = {
                        isDialogVisible = false
                        if (result.contains("thành công")) {
                            viewModel.getRooms(houseId.toString())
                        }
                        viewModel.updateResult("")
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

