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

    object RentalHouseScreen : Route(route = "rentalHouseScreen/{houseId}")

    object AddressLocationScreen : Route(route = "addressLocationScreen")

    object AddAssetScreen : Route(route = "addAssetScreen")

    object AddContractScreen : Route(route = "addContractScreen")

    object MoreInformationScreen: Route(route = "moreInformationScreen")

    object ServiceScreen : Route(route = "serviceScreen")

    object AddServiceScreen : Route(route = "addServiceScreen")

    object ContractScreen: Route(route = "contractScreen")

    object InvoiceScreen: Route(route = "invoiceScreen")

    object AssetScreen: Route(route = "assetScreen")

    object RoomScreen: Route(route = "roomScreen/{houseId}/{houseName}")

    object CreateRoomScreen: Route(route = "createRoomScreen/{houseId}")

    object UserInformationScreen: Route(route = "userInformationScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object AuthNavigation : Route(route = "authNavigation")

    object RoomRentingNavigation : Route(route = "roomRentingNavigation")

    object RoomRentingNavigator : Route(route = "roomRentingNavigator")
}

