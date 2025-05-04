package com.ahrorovk.myapplication.app.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahrorovk.myapplication.app.navigation.components.PrayerfulPathBottomBar
import com.ahrorovk.myapplication.core.Routes
import com.ahrorovk.myapplication.core.doesScreenHaveBottomBar
import com.ahrorovk.myapplication.presentation.prayerTimesScreen.PrayTimeViewModel
import com.ahrorovk.myapplication.presentation.prayerTimesScreen.PrayerTimesScreen
import com.ahrorovk.myapplication.presentation.zikrScreen.CounterScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "NewApi")
@Composable
fun Navigation(
    navigationViewModel: NavigationViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val currentScreen = navController.currentDestination?.route ?: ""
    Scaffold(
        bottomBar = {
            if (doesScreenHaveBottomBar(currentScreen)) {
                PrayerfulPathBottomBar(navController)
            }
        }
    ) { it_ ->
        NavHost(
            modifier = Modifier.padding(it_),
            navController = navController,
            startDestination = Routes.PrayTimeScreen.route
        ) {

            composable(Routes.PrayTimeScreen.route) {
                val viewModel = hiltViewModel<PrayTimeViewModel>()
                val state = viewModel.state.collectAsState()

                PrayerTimesScreen(
                    state = state.value,
                    onEvent = { event ->
                        when (event) {
                            else -> viewModel.onEvent(event)
                        }
                    }
                )
            }
            composable(Routes.ZikrScreen.route) {
                CounterScreen()
            }
        }
    }
}