package com.example.elders.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.elders.screens.AddNewMembersScreen
import com.example.elders.screens.LogMeetingDataScreen
import com.example.elders.screens.HomeScreen
import com.example.elders.screens.ViewMembersScreen
import com.example.elders.screens.WelcomeScreen

@Composable
fun NavigationController(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ROUTE_HOME_SCREEN,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(400),
                initialOffsetX = { fullWidth ->
                    -fullWidth / 3
                }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(400)
            )
        },
        popEnterTransition = {
            slideInHorizontally(animationSpec = tween(400))
        },
        popExitTransition = {
            slideOutHorizontally(animationSpec = tween(400))
        }
    ){
        composable(ROUTE_WELCOME_SCREEN) {
            WelcomeScreen(navController = navController)
        }
        composable(ROUTE_HOME_SCREEN){
            HomeScreen(navController = navController)
        }
        composable(ROUTE_ADD_NEW_MEMBER){
            AddNewMembersScreen(navController = navController)
        }
        composable(ROUTE_VIEW_MEMBERS){
            ViewMembersScreen()
        }
        composable(ROUTE_CREATE_MEETING_ENTRY) {
            LogMeetingDataScreen()
        }
    }
}