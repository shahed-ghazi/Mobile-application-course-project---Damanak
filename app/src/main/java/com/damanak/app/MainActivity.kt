package com.damanak.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.damanak.app.ui.navigation.AppNavHost
import com.damanak.app.ui.theme.DamanakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DamanakTheme {
                AppNavHost(rememberNavController())
            }
        }
    }
}
