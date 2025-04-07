package com.salmaazizah0040.hydrocalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.salmaazizah0040.hydrocalc.ui.screen.App
import com.salmaazizah0040.hydrocalc.ui.theme.HydroCalcTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HydroCalcTheme {
                App()
            }
        }
    }
}
