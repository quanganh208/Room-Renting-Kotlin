package com.itptit.roomrenting.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.itptit.roomrenting.presentation.auth.login.LoginScreen
import com.itptit.roomrenting.presentation.auth.login.LoginViewModel
import com.itptit.roomrenting.presentation.auth.register.RegisterScreen
import com.itptit.roomrenting.presentation.auth.register.RegisterViewModel
import com.itptit.roomrenting.presentation.navgraph.roomrenting_navigator.RoomRentingNavigator
import com.itptit.roomrenting.presentation.onboarding.OnBoardingScreen
import com.itptit.roomrenting.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route, startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(route = Route.AuthNavigation.route, startDestination = Route.LoginScreen.route) {
            composable(route = Route.LoginScreen.route) {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreen(
                    navController = navController, onLoginSuccess = {
                        navController.navigate(Route.RoomRentingNavigation.route) {
                            popUpTo(Route.LoginScreen.route) { inclusive = true }
                        }
                    }, viewModel = viewModel
                )
            }

            composable(route = Route.RegisterScreen.route) {
                val viewModel: RegisterViewModel = hiltViewModel()
                RegisterScreen(
                    navController = navController, onRegisterSuccess = {
                        navController.navigate(Route.LoginScreen.route) {
                            popUpTo(Route.RegisterScreen.route) { inclusive = true }
                        }
                    }, viewModel = viewModel
                )
            }
        }

        navigation(
            route = Route.RoomRentingNavigation.route,
            startDestination = Route.RoomRentingNavigator.route
        ) {
            composable(route = Route.RoomRentingNavigator.route) {
                RoomRentingNavigator(sharedNavController = navController)
            }
        }
    }
}

