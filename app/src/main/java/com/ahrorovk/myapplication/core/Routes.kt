package com.ahrorovk.myapplication.core



sealed class Routes(val route: String) {
    object PrayTimeScreen : Routes("PrayTimeScreen")
    object ZikrScreen : Routes("ZikrScreen")
    object QuranScreen : Routes("QuranScreen")
    object SettingsScreen : Routes("SettingsScreen")
}

sealed class BottomSheets(val route: String) {
}