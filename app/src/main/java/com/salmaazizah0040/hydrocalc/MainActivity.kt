package com.salmaazizah0040.hydrocalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.salmaazizah0040.hydrocalc.ui.screen.MainScreen
import com.salmaazizah0040.hydrocalc.ui.theme.HydroCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            HydroCalcTheme {
                MainScreen()
            }
        }
    }
}
