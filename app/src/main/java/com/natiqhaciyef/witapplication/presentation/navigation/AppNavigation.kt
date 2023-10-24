package com.natiqhaciyef.witapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.natiqhaciyef.witapplication.presentation.screens.home.MainScreenLine
import com.natiqhaciyef.witapplication.presentation.screens.home.learn.CustomPlanScreen
import com.natiqhaciyef.witapplication.presentation.screens.home.learn.FieldScreen
import com.natiqhaciyef.witapplication.presentation.screens.home.learn.MaterialScreen
import com.natiqhaciyef.witapplication.presentation.screens.home.learn.interview.InterviewQuestions
import com.natiqhaciyef.witapplication.presentation.screens.home.learn.interview.LiveInterviewScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.ForgotPasswordScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.LoginScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.RegisterScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenId.SplashScreen.name) {
        composable(ScreenId.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(ScreenId.ForgotPasswordScreen.name) {
            ForgotPasswordScreen(navController = navController)
        }

        composable(ScreenId.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(ScreenId.RegistrationScreen.name) {
            RegisterScreen(navController = navController)
        }

        composable(ScreenId.MainScreenLine.name) {
            MainScreenLine(navController = navController)
        }


        composable(
            route = "${ScreenId.FieldScreen.name}/info",
            arguments = listOf(
                navArgument(name = "info") {
                    type = NavType.StringType
                }
            )
        ) {
            val info = it.arguments?.getString("info") ?: ""
            FieldScreen(
                navController = navController,
                info = info
            )
        }

        composable(ScreenId.CustomPlanScreen.name) {
            CustomPlanScreen(navController)
        }

        composable(
            route = "${ScreenId.MaterialScreen.name}/field",
            arguments = listOf(
                navArgument("field") {
                    type = NavType.StringType
                }
            )
        ) {
            val field = it.arguments?.getString("field") ?: ""

            MaterialScreen(navController, field)
        }

        composable(
            route = "${ScreenId.InterviewQuestionsScreen.name}/field",
            arguments = listOf(
                navArgument("field"){
                    type = NavType.StringType
                }
            )
        ) {
            val field = it.arguments?.getString("field") ?: ""
            InterviewQuestions(navController, field)
        }

        composable(ScreenId.LiveInterviewScreen.name) {
            LiveInterviewScreen(navController)
        }
    }
}