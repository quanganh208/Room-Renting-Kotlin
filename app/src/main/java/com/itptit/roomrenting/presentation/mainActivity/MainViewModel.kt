package com.itptit.roomrenting.presentation.mainActivity

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.itptit.roomrenting.domain.usecases.app_entry.AppEntryUseCases
import com.itptit.roomrenting.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases, application: Application
) : AndroidViewModel(application) {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            val shouldStartFromHomeScreen = appEntryUseCases.readAppEntry().first()
            if (shouldStartFromHomeScreen) {
                checkLoginStatus()
            } else {
                _startDestination.value = Route.AppStartNavigation.route
            }
            delay(300)
            _splashCondition.value = false
        }
    }

    private fun checkLoginStatus() {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        val accessToken = sharedPreferences.getString("access_token", null)
        _startDestination.value = if (accessToken != null) {
            Route.RoomRentingNavigation.route
        } else {
            Route.AuthNavigation.route
        }
    }
}