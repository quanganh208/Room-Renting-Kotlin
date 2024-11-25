package com.itptit.roomrenting.presentation.mainActivity

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.itptit.roomrenting.domain.model.auth.login.Data
import com.itptit.roomrenting.domain.usecases.app_entry.ReadAppEntry
import com.itptit.roomrenting.presentation.navgraph.Route
import com.itptit.roomrenting.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    readAppEntry: ReadAppEntry, application: Application
) : AndroidViewModel(application) {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    init {
        readAppEntry().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen) {
                checkLoginStatus()
            } else {
                _startDestination.value = Route.AppStartNavigation.route
            }
            delay(500) //Without this delay, the onBoarding screen will show for a momentum.
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }

    private fun checkLoginStatus() {
        val sharedPreferences =
            getApplication<Application>().getSharedPreferences(
                Constants.LOGIN_PREFS,
                Context.MODE_PRIVATE
            )
        val gson = Gson()
        val dataJson = sharedPreferences.getString(Constants.USER_DATA, null)
        val accessToken = gson.fromJson(dataJson, Data::class.java)?.jwt
        _startDestination.value = if (accessToken != null) {
            Route.RoomRentingNavigation.route
        } else {
            Route.AuthNavigation.route
        }
    }
}