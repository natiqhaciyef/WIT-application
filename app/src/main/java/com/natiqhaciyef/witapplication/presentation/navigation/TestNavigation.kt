package com.natiqhaciyef.witapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun TestNavigation(
    navController: NavController = rememberNavController(),
    onAction: (String) -> Unit = {}
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = ScreenId.MainTestScreen.name
    ) {
        composable(ScreenId.MainTestScreen.name) {
            onAction("Main Test Screen tested")
        }

        composable(ScreenId.TestScreen.name) {
            onAction("Test Screen tested")
        }
    }
}