package com.itptit.roomrenting.presentation.auth.home

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.itptit.roomrenting.presentation.navgraph.Route

@Composable
fun HomeScreen(
    navController: NavController,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        Button(
            onClick = {
                val sharedPreferences =
                    navController.context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    remove("access_token")
                    apply()
                }
                navController.navigate(Route.LoginScreen.route) {
                    popUpTo(Route.HomeScreen.route) { inclusive = true }
                }
            }
        ) {
            Text(text = "Logout")
        }
    }
}


