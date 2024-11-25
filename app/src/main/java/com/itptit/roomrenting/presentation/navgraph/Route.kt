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

    object RentalHouseScreen : Route(route = "rentalHouseScreen")

    object AddressLocationScreen : Route(route = "addressLocationScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object AuthNavigation : Route(route = "authNavigation")

    object RoomRentingNavigation : Route(route = "roomRentingNavigation")
}

