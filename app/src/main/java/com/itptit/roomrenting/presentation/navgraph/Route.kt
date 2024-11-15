package com.itptit.roomrenting.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object LoginScreen : Route(route = "loginScreen")

    object RegisterScreen : Route(route = "registerScreen")

    object HomeScreen : Route(route = "homeScreen")

    object BookmarkScreen : Route(route = "bookMarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object RoomRentingNavigation : Route(route = "roomRentingNavigation")
}

