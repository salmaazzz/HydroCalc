package com.salmaazizah0040.hydrocalc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salmaazizah0040.hydrocalc.ui.screen.AboutScreen
import com.salmaazizah0040.hydrocalc.ui.screen.MainScreen
import com.salmaazizah0040.hydrocalc.ui.screen.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen()
        }
    }
}