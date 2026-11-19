package com.zion.elders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.zion.elders.navigation.NavigationController
import com.zion.elders.screens.ProjectsScreen
import com.zion.elders.ui.theme.EldersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EldersTheme {
                val navHostController = rememberNavController()
                NavigationController(navHostController)
//                ProjectsScreen()
            }
        }
    }
}