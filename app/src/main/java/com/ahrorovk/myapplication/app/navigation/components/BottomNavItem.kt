package com.ahrorovk.myapplication.app.navigation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ahrorovk.myapplication.core.Routes
import com.ahrorovk.myapplication.data.model.navigation.BottomNavDestination

@Composable
fun RowScope.BottomNavItem(
    navController: NavController,
    item: BottomNavDestination
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
            when (item.destinationRoute) {
                Routes.PrayTimeScreen.route -> {
                    it.route == Routes.PrayTimeScreen.route
                }

                Routes.ZikrScreen.route -> {
                    it.route == Routes.ZikrScreen.route
                }

                else -> {
                    it.route == Routes.PrayTimeScreen.route
                }
            }
        } == true,
        onClick = {
            if (currentDestination?.route != item.destinationRoute)
                navigateToScreen(item.destinationRoute, navController)
        },
        icon = {
            Icon(
                painter = painterResource(id = item.icon),
                modifier = Modifier.size(24.dp),
                contentDescription = "BottomNavIcon"
            )
        },
        selectedContentColor = MaterialTheme.colorScheme.surface,
        unselectedContentColor = MaterialTheme.colorScheme.onBackground
    )
}

private fun navigateToScreen(route: String, navController: NavController) {
    navController.navigate(route = route) {
        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}