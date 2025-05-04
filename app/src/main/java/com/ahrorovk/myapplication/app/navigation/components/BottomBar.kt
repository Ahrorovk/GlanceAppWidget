package com.ahrorovk.myapplication.app.navigation.components

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ahrorovk.myapplication.R
import com.ahrorovk.myapplication.app.ui.theme.Purple700
import com.ahrorovk.myapplication.core.Routes
import com.ahrorovk.myapplication.data.model.navigation.BottomNavDestination

@Composable
fun PrayerfulPathBottomBar(navController: NavController) {
    BottomNavigation(
        backgroundColor = Purple700,
        contentColor = Purple700
    ) {
        bottomNavDestinations.forEach { navItem ->
            BottomNavItem(navController = navController, item = navItem)
        }
    }
}

val bottomNavDestinations = listOf(
    BottomNavDestination(
        label = "Time",
        destinationRoute = Routes.PrayTimeScreen.route,
        icon = R.drawable.__access_time
    ),
    BottomNavDestination(
        label = "Zikr",
        destinationRoute = Routes.ZikrScreen.route,
        icon = R.drawable.rosary
    )
)