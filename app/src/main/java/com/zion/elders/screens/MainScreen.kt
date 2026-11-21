package com.zion.elders.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.zion.elders.components.BottomNavBar
import com.zion.elders.components.backgroundColor
import com.zion.elders.models.AppTab
import com.zion.elders.screens.meetings.MeetingsScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController){
    var activeTab by remember { mutableStateOf(AppTab.HOME) }
    var error by remember { mutableStateOf("") }

    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val scope = rememberCoroutineScope()



    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            BottomNavBar(currentTab = activeTab) { selectedTab ->
                activeTab = selectedTab
            }
        }
    ) { innerPadding ->
        LaunchedEffect(Unit) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Error occurred: $error",
                    withDismissAction = true,
                    duration = SnackbarDuration.Short
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            when(activeTab) {
                AppTab.HOME -> {
                    HomeScreen(navController, onErrorOccurred = { error = it })
                }

                AppTab.MEETINGS -> {
                    MeetingsScreen(navController)
                }

                AppTab.PROJECTS -> {
                    ProjectsScreen()
                }
            }
        }
    }
}