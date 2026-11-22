package com.zion.elders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.zion.elders.models.MessageType
import com.zion.elders.models.ResultData
import com.zion.elders.navigation.NavigationController
import com.zion.elders.screens.ProjectsScreen
import com.zion.elders.screens.meetings.LogMeetingDataScreen
import com.zion.elders.screens.messenger.WhatHappened
import com.zion.elders.screens.minutes.MinutesScreen
import com.zion.elders.ui.theme.EldersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EldersTheme {
//                MinutesScreen(rememberNavController())
                val navHostController = rememberNavController()
                NavigationController(navHostController)
//                WhatHappened(
//                    ResultData(MessageType.SUCCESS, "Member added!", "Successfully added new member to the system!")
//                )
//                ProjectsScreen()
//                LogMeetingDataScreen(rememberNavController())
            }
        }
    }
}