package com.zion.elders.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zion.elders.screens.HomeScreen
import com.zion.elders.screens.meetings.LogMeetingDataScreen
import com.zion.elders.screens.MainScreen
import com.zion.elders.screens.WelcomeScreen
import com.zion.elders.screens.meetings.MinutesScreen

@Composable
fun NavigationController(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = ROUTE_MAIN,
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
        composable(ROUTE_MAIN) {
            MainScreen(navController = navController)
        }
        composable(ROUTE_WELCOME_SCREEN) {
            WelcomeScreen(navController = navController)
        }
        composable(ROUTE_HOME_SCREEN){
            HomeScreen(navController = navController)
        }
        composable(ROUTE_ADD_NEW_MEMBER){
//            AddNewMembersScreen(navController = navController)
        }
        composable(ROUTE_VIEW_MEMBERS){
//            ViewMembersScreen()
        }
        composable(ROUTE_CREATE_MEETING_ENTRY) {
            LogMeetingDataScreen(navController = navController)
        }
        composable(ROUTE_MINUTES_SCREEN) {
            MinutesScreen(navController)
        }
    }
}