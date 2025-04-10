package com.salmaazizah0040.hydrocalc.navigation

sealed class Screen(val route:String) {
    data object Splash: Screen("splashScreen")
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Tips: Screen("tipsScreen")
}