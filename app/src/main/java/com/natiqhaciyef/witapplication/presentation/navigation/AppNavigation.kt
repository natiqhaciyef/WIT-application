package com.natiqhaciyef.witapplication.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.natiqhaciyef.witapplication.common.util.objects.DefaultImpl
import com.natiqhaciyef.witapplication.presentation.navigation.type.PostType
import com.natiqhaciyef.witapplication.presentation.screens.main.MainScreenLine
import com.natiqhaciyef.witapplication.presentation.screens.main.home.DetailsScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.learn.CustomPlanScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.learn.FieldScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.learn.MaterialScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.learn.interview.InterviewQuestions
import com.natiqhaciyef.witapplication.presentation.screens.main.learn.interview.LiveInterviewScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.user.HelpScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.user.LanguageScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.user.ResetPasswordScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.user.SavedContactScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.user.SavedPostScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.user.SavedQuestionsScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.ForgotPasswordScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.LoginScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.RegisterScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.SplashScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
            route = "${ScreenId.FieldScreen.name}/{info}",
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
            route = "${ScreenId.MaterialScreen.name}/{field}",
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
            route = "${ScreenId.InterviewQuestionsScreen.name}/{field}",
            arguments = listOf(
                navArgument("field") {
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


        composable(
            route = "${ScreenId.DetailsScreen.name}/{post}",
            arguments = listOf(
                navArgument("post") {
                    type = PostType(false)
                }
            )
        ) {
            val post = it.arguments?.getParcelable("post") ?: DefaultImpl.post

            DetailsScreen(navController = navController, postModel = post)
        }



        composable(ScreenId.SavedContactScreen.name) {
            SavedContactScreen(navController = navController)
        }

        composable(ScreenId.SavedQuestionsScreen.name) {
            SavedQuestionsScreen(navController = navController)
        }

        composable(ScreenId.SavedPostsScreen.name) {
            SavedPostScreen(navController = navController)
        }

        composable(ScreenId.HelpScreen.name) {
            HelpScreen()
        }

        composable(ScreenId.LanguageScreen.name) {
            LanguageScreen(navController = navController)
        }

        composable(ScreenId.ResetPasswordScreen.name) {
            ResetPasswordScreen(navController = navController)
        }

    }
}