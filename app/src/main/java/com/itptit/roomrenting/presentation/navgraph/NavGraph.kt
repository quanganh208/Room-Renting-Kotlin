package com.itptit.roomrenting.presentation.navgraph

import AddressLocationScreen
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.google.gson.Gson
import com.itptit.roomrenting.domain.model.auth.login.Data
import com.itptit.roomrenting.presentation.home.main.HomeScreen
import com.itptit.roomrenting.presentation.auth.login.LoginScreen
import com.itptit.roomrenting.presentation.auth.login.LoginViewModel
import com.itptit.roomrenting.presentation.auth.register.RegisterScreen
import com.itptit.roomrenting.presentation.auth.register.RegisterViewModel
import com.itptit.roomrenting.presentation.home.addresslocation.AddressLocationViewModel
import com.itptit.roomrenting.presentation.home.rentalhouse.RentalHouseScreen
import com.itptit.roomrenting.presentation.onboarding.OnBoardingScreen
import com.itptit.roomrenting.presentation.onboarding.OnBoardingViewModel
import com.itptit.roomrenting.util.Constants

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(route = Route.AuthNavigation.route, startDestination = Route.LoginScreen.route) {
            composable(route = Route.LoginScreen.route) {
                val viewModel: LoginViewModel =
                    hiltViewModel() // Using Hilt for dependency injection
                LoginScreen(
                    navController = navController, onLoginSuccess = {
                        navController.navigate(Route.HomeScreen.route) {
                            popUpTo(Route.LoginScreen.route) { inclusive = true }
                        }
                    }, viewModel = viewModel
                )
            }

            composable(route = Route.RegisterScreen.route) {
                val viewModel: RegisterViewModel =
                    hiltViewModel() // Using Hilt for dependency injection
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
            startDestination = hasHouse(navController.context)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: RegisterViewModel =
                    hiltViewModel()
                HomeScreen(
                    navController = navController,
                )
            }
            composable(route = Route.RentalHouseScreen.route) {
                RentalHouseScreen(navController = navController)
            }
            composable(route = Route.AddressLocationScreen.route) {
                val viewModel: AddressLocationViewModel =
                    hiltViewModel()
                AddressLocationScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}

private fun hasHouse(context: Context): String {
    val sharedPreferences = context.getSharedPreferences(
        Constants.LOGIN_PREFS,
        Context.MODE_PRIVATE
    )
    val gson = Gson()
    val dataJson = sharedPreferences.getString(Constants.USER_DATA, null)
    val authorities = gson.fromJson(dataJson, Data::class.java)?.user?.authorities
    return authorities?.find { it.authority == "OWNER" }?.let {
        Route.HomeScreen.route
    } ?: Route.RentalHouseScreen.route
}