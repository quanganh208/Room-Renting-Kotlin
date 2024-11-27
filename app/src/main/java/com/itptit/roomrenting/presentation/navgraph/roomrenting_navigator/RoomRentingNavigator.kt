package com.itptit.roomrenting.presentation.navgraph.roomrenting_navigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.home.main.HomeScreen
import com.itptit.roomrenting.presentation.home.moreinfo.MoreInformationScreen
import com.itptit.roomrenting.presentation.navgraph.Route
import com.itptit.roomrenting.presentation.navgraph.roomrenting_navigator.components.BottomNavigationItem
import com.itptit.roomrenting.presentation.navgraph.roomrenting_navigator.components.RoomRentingBottomNavigation
import com.itptit.roomrenting.presentation.service.AddServiceScreen
import com.itptit.roomrenting.presentation.service.ServiceScreen

@Composable
fun RoomRentingNavigator(sharedNavController: NavController) {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Trang chủ"),
            BottomNavigationItem(icon = R.drawable.ic_user, text = "Cá nhân"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.MoreInformationScreen.route -> 1
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.MoreInformationScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            RoomRentingBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.MoreInformationScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen(
                    navController = navController,
                )
            }
            composable(route = Route.MoreInformationScreen.route) {
                MoreInformationScreen(
                    onLogoutSuccess = {
                        navigateToLogin(sharedNavController)
                    }
                )
            }

            composable(route = Route.ServiceScreen.route) {
                OnBackClickStateSaver(navController)
                ServiceScreen(navigaToThemDV = {
                    navController.navigate(Route.AddServiceScreen.route)
                }, onBack = {
                    navController.popBackStack()
                })
            }
            composable(route = Route.AddServiceScreen.route) {
                AddServiceScreen(onBack = {
                    navController.popBackStack()
                })
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToLogin(sharedNavController: NavController) {
    sharedNavController.navigate(Route.AuthNavigation.route) {
        popUpTo(0) { inclusive = true }
    }
}