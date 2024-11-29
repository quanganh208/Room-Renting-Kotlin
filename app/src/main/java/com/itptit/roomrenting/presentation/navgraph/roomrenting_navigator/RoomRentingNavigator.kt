package com.itptit.roomrenting.presentation.navgraph.roomrenting_navigator

import com.itptit.roomrenting.presentation.home.rentalhouse.RentalHouseViewModel
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itptit.roomrenting.R
import com.itptit.roomrenting.presentation.home.addasset.AddAssetScreen
import com.itptit.roomrenting.presentation.home.addasset.AddAssetViewModel
import com.itptit.roomrenting.presentation.home.addcontract.AddContractScreen
import com.itptit.roomrenting.presentation.home.addcontract.AddContractViewModel
import com.itptit.roomrenting.presentation.home.addresslocation.AddressLocationScreen
import com.itptit.roomrenting.presentation.home.addresslocation.AddressLocationViewModel
import com.itptit.roomrenting.presentation.home.createcontract.CreateContractScreen
import com.itptit.roomrenting.presentation.home.main.HomeScreen
import com.itptit.roomrenting.presentation.home.main.HomeViewModel
import com.itptit.roomrenting.presentation.home.moreinfo.MoreInformationScreen
import com.itptit.roomrenting.presentation.home.rentalhouse.RentalHouseScreen
import com.itptit.roomrenting.presentation.home.userinfo.UserInformationScreen
import com.itptit.roomrenting.presentation.navgraph.Route
import com.itptit.roomrenting.presentation.navgraph.roomrenting_navigator.components.BottomNavigationItem
import com.itptit.roomrenting.presentation.navgraph.roomrenting_navigator.components.RoomRentingBottomNavigation
import com.itptit.roomrenting.presentation.other.AssetScreen
import com.itptit.roomrenting.presentation.other.ContractScreen
import com.itptit.roomrenting.presentation.other.InvoiceScreen
import com.itptit.roomrenting.presentation.room.CreateRoomScreen
import com.itptit.roomrenting.presentation.room.CreateRoomViewModel
import com.itptit.roomrenting.presentation.room.RoomScreen
import com.itptit.roomrenting.presentation.room.RoomViewModel
import com.itptit.roomrenting.presentation.room.asset.AssetRoomViewModel
import com.itptit.roomrenting.presentation.room.asset.QLTaiSan
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
            modifier = Modifier.padding(bottom = if (isBottomBarVisible) bottomPadding else 0.dp)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    navController = navController, viewModel = viewModel
                )
            }
            composable(route = Route.MoreInformationScreen.route) {
                MoreInformationScreen(
                    onLogoutSuccess = {
                        navigateToLogin(sharedNavController)
                    },
                    navController = navController
                )
            }

            composable(route = "${Route.ServiceScreen.route}/{houseName}") { backStackEntry ->
                val houseName = backStackEntry.arguments?.getString("houseName")
                OnBackClickStateSaver(navController)
                ServiceScreen(navigaToThemDV = {
                    navController.navigate("${Route.AddServiceScreen.route}/$houseName")
                }, onBack = {
                    navController.popBackStack()
                },
                    houseName = houseName ?: ""
                )
            }
            composable(route = "${Route.AddServiceScreen.route}/{houseName}") { backStackEntry ->
                val houseName = backStackEntry.arguments?.getString("houseName")
                AddServiceScreen(onBack = {
                    navController.popBackStack()
                }, houseName = houseName ?: "")
            }
            composable(route = "${Route.ContractScreen.route}/{houseName}") { backStackEntry ->
                val houseName = backStackEntry.arguments?.getString("houseName")
                OnBackClickStateSaver(navController)
                ContractScreen(navController = navController, houseName = houseName ?: "")
            }

            composable(route = "${Route.RoomScreen.route}/{houseId}/{houseName}") { backStackEntry ->
                val houseId = backStackEntry.arguments?.getString("houseId") ?: "0"
                val houseName = backStackEntry.arguments?.getString("houseName")
                val viewModel: RoomViewModel = hiltViewModel()
                RoomScreen(
                    navController = navController,
                    houseId = houseId.toInt(),
                    houseName = houseName ?: "",
                    viewModel = viewModel
                )
            }
            composable(route = "${Route.CreateRoomScreen.route}/{houseId}/{roomId}") { backStackEntry ->
                val houseId = backStackEntry.arguments?.getString("houseId")
                val roomId = backStackEntry.arguments?.getString("roomId")
                val viewModel: CreateRoomViewModel = hiltViewModel()
                OnBackClickStateSaver(navController)
                CreateRoomScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    houseId = houseId ?: "",
                    roomId = roomId ?: "",
                    viewModel = viewModel
                )
            }

            composable(route = "${Route.AssetRoomScreen.route}/{roomId}/{nameRoom}") { backStackEntry ->
                val roomId = backStackEntry.arguments?.getString("roomId")
                val nameRoom = backStackEntry.arguments?.getString("nameRoom")
                val viewModel: AssetRoomViewModel = hiltViewModel()
                QLTaiSan(
                    roomId = roomId ?: "",
                    nameRoom = nameRoom ?: "",
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(route = "${Route.InvoiceScreen.route}/{houseName}") { backStackEntry ->
                val houseName = backStackEntry.arguments?.getString("houseName")
                OnBackClickStateSaver(navController)
                InvoiceScreen(navController = navController, houseName = houseName ?: "")
            }

            composable(route = "${Route.AssetScreen.route}/{houseName}") { backStackEntry ->
                val houseName = backStackEntry.arguments?.getString("houseName")
                OnBackClickStateSaver(navController)
                AssetScreen(navController = navController, nameHouse = houseName ?: "")
            }

            composable(route = "${Route.RentalHouseScreen.route}/{houseId}") { backStackEntry ->
                val houseId = backStackEntry.arguments?.getString("houseId")
                val viewModel: RentalHouseViewModel = hiltViewModel()
                houseId?.let { it1 ->
                    RentalHouseScreen(
                        navController = navController,
                        viewModel = viewModel,
                        houseId = it1
                    )
                }
            }
            composable(route = Route.AddressLocationScreen.route) {
                val viewModel: AddressLocationViewModel = hiltViewModel()
                AddressLocationScreen(navController = navController, viewModel = viewModel)
            }

            composable(route = Route.UserInformationScreen.route) {
                UserInformationScreen(
                    navController = navController,
                    onLogoutSuccess = {
                        navigateToLogin(sharedNavController)
                    },
                )
            }

            composable(route = "${Route.AddAssetScreen.route}/{roomId}/{nameRoom}/{assetId}") { backStackEntry ->
                val roomId = backStackEntry.arguments?.getString("roomId")
                val nameRoom = backStackEntry.arguments?.getString("nameRoom")
                val assetId = backStackEntry.arguments?.getString("assetId")
                val viewModel: AddAssetViewModel = hiltViewModel()
                AddAssetScreen(
                    navController = navController,
                    viewModel = viewModel,
                    roomId = roomId ?: "",
                    nameRoom = nameRoom ?: "",
                    assetId = assetId ?: ""
                )
            }
            composable(route = Route.AddContractScreen.route) {
                val viewModel: AddContractViewModel = hiltViewModel()
                AddContractScreen(navController = navController, viewModel = viewModel)
            }

            composable(route = "${Route.CreateContractScreen.route}/{roomId}") { backStackEntry ->
                val roomId = backStackEntry.arguments?.getString("roomId")
                CreateContractScreen(navController = navController, roomId = roomId ?: "")
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