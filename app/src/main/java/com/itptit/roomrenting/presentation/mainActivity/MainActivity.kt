package com.itptit.roomrenting.presentation.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.itptit.roomrenting.data.remote.ApiClient
import com.itptit.roomrenting.presentation.navgraph.NavGraph
import com.itptit.roomrenting.ui.theme.RoomRentingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.jakewharton.threetenabp.AndroidThreeTen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        ApiClient.init(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition.value
            }
        }
        setContent {
            RoomRentingAppTheme(dynamicColor = false) {
                val isSystemInDarkTheme = isSystemInDarkTheme()
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkTheme
                    )
                }
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    NavGraph(startDestination = viewModel.startDestination.value)
                }
            }
        }
    }
}
