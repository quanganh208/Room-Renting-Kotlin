package com.itptit.roomrenting.presentation.home.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val isLoading by viewModel.isLoading.collectAsState()
    val houses by viewModel.houses.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getOwnerHouses()
    }

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (houses.data.isNotEmpty()) {
        MainScreen(navController = navController, houses = houses)
    } else {
        EmptyHomeScreen(navController = navController)
    }
}