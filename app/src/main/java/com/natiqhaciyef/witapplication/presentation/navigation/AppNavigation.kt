package com.natiqhaciyef.witapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.witapplication.presentation.screens.register.ForgotPasswordScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.LoginScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.RegisterScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenId.SplashScreen.name){
        composable(ScreenId.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(ScreenId.ForgotPasswordScreen.name){
            ForgotPasswordScreen(navController = navController)
        }

        composable(ScreenId.LoginScreen.name){
            LoginScreen(navController = navController)
        }

        composable(ScreenId.RegistrationScreen.name){
            RegisterScreen(navController = navController)
        }

    }
}