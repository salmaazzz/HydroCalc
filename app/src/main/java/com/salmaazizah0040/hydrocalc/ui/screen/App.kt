package com.salmaazizah0040.hydrocalc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.salmaazizah0040.hydrocalc.R
import kotlinx.coroutines.delay

@Composable
fun App() {
    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2500)
        showSplash = false
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFD7FEFD)
    ) {
        if (showSplash) {
            SplashScreen()
        } else {
            MainScreen()
        }
    }
}

@Composable
fun SplashScreen() {
    var alpha by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        alpha = 1f
    }

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