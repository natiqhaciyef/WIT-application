package com.natiqhaciyef.witapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.witapplication.presentation.screens.main.user.UserProfileScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.SplashScreen

@Composable
fun NetworkLessNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenId.UserProfileScreen.name) {
        composable(ScreenId.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(ScreenId.UserProfileScreen.name) {
            UserProfileScreen(navController = navController)
        }
    }
}