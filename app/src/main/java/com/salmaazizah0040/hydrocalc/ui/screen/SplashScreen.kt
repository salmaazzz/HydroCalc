package com.salmaazizah0040.hydrocalc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.salmaazizah0040.hydrocalc.R
import com.salmaazizah0040.hydrocalc.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var alpha by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        alpha = 1f
        delay(2500)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFD7FEFD)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.splash_image),
                contentDescription = "Splash Logo",
                modifier = Modifier.size(330.dp)
            )
        }
    }
}