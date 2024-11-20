package com.itptit.roomrenting.presentation.auth.home

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.itptit.roomrenting.presentation.navgraph.Route
import com.itptit.roomrenting.util.Constants

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {
    val houses by homeViewModel.houses.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Column {
            Button(
                onClick = {
                    val sharedPreferences =
                        navController.context.getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE)
                    with(sharedPreferences.edit()) {
                        remove(Constants.ACCESS_TOKEN)
                        apply()
                    }
                    navController.navigate(Route.LoginScreen.route) {
                        popUpTo(Route.HomeScreen.route) { inclusive = true }
                    }
                }
            ) {
                Text(text = "Logout")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    homeViewModel.getHouses()
                }
            ) {
                Text(text = "Get House")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = houses)
            }
        }
    }
}