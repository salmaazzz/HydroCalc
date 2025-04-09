package com.salmaazizah0040.hydrocalc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salmaazizah0040.hydrocalc.ui.screen.AboutScreen
import com.salmaazizah0040.hydrocalc.ui.screen.MainScreen
import com.salmaazizah0040.hydrocalc.ui.screen.SplashScreen
import com.salmaazizah0040.hydrocalc.ui.screen.TipsScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController(),
                  isDarkTheme: Boolean,
                  onToggleTheme: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.Home.route) {
            MainScreen(navController, isDarkTheme, onToggleTheme)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(Screen.Tips.route) {
            TipsScreen(navController)
        }
    }
}