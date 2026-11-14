package com.zion.elders.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zion.elders.components.BottomNavBar
import com.zion.elders.models.AppTab
import com.zion.elders.navigation.NavigationController

@Composable
fun MainScreen(navController: NavHostController){
    var activeTab by remember { mutableStateOf(AppTab.HOME) }
    val creamCanvas = Color(0xFFFAF6F1)

    Scaffold(
        containerColor = creamCanvas,
        bottomBar = {
            BottomNavBar(currentTab = activeTab) { selectedTab ->
                activeTab = selectedTab
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            when(activeTab) {
                AppTab.HOME -> {
                    HomeScreen(navController)
                }

                AppTab.MEETINGS -> {
                    MeetingsScreen(navController)
                }
                else -> {
                    ""
                }
            }
        }
    }
}