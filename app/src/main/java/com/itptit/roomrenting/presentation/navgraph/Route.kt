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

    object AddAssetScreen : Route(route = "addAssetScreen/{roomId}/{nameRoom}/{assetId}")

    object AddContractScreen : Route(route = "addContractScreen")

    object MoreInformationScreen: Route(route = "moreInformationScreen")

    object ServiceScreen : Route(route = "serviceScreen/{houseName}")

    object AddServiceScreen : Route(route = "addServiceScreen/{houseName}")

    object ContractScreen: Route(route = "contractScreen/{houseName}")

    object InvoiceScreen: Route(route = "invoiceScreen/{houseName}")

    object AssetScreen: Route(route = "assetScreen/{houseName}")

    object RoomScreen: Route(route = "roomScreen/{houseId}/{houseName}")

    object CreateRoomScreen: Route(route = "createRoomScreen/{houseId}/{roomId}")

    object AssetRoomScreen: Route(route = "assetRoomScreen/{roomId}/{nameRoom}")

    object UserInformationScreen: Route(route = "userInformationScreen")

    object CreateContractScreen: Route(route = "createContractScreen/{roomId}")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object AuthNavigation : Route(route = "authNavigation")

    object RoomRentingNavigation : Route(route = "roomRentingNavigation")

    object RoomRentingNavigator : Route(route = "roomRentingNavigator")
}

