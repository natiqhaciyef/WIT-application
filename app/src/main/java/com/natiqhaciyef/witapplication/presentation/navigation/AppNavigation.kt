package com.natiqhaciyef.witapplication.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.witapplication.presentation.navigation.type.PostType
import com.natiqhaciyef.witapplication.presentation.screens.main.MainScreenLine
import com.natiqhaciyef.witapplication.presentation.screens.main.details.DetailsScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.notification.NotificationScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.customplan.CustomPlanScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.field.FieldScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.materials.MaterialScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.exam.ExamScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.exam.StartExamScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.interview.InterviewQuestions
import com.natiqhaciyef.witapplication.presentation.screens.main.interview.LiveInterviewScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.help.HelpScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.language.LanguageScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.forgotpassword.ResetPasswordScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.lastcontacts.LastContactScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.details.LikedPostScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.interview.SavedQuestionsScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.verifyaccount.VerifyAccountScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.forgotpassword.ForgotPasswordScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.login.LoginScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.registration.RegisterScreen
import com.natiqhaciyef.witapplication.presentation.screens.register.onboard.SplashScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation(onStop: Boolean) {
    val navController = rememberNavController()
    if (onStop)
        navController.clearBackStack(ScreenId.MainScreenLine.name)

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


        composable(ScreenId.ExamScreen.name) {
            ExamScreen(navController = navController)
        }

        composable(
            route = "${ScreenId.StartExamScreen.name}/{field}",
            arguments = listOf(
                navArgument("field") { type = NavType.StringType }
            )
        ) {
            val field = it.arguments?.getString("field") ?: ""
            StartExamScreen(navController = navController, field = field)
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

        composable(
            route = "${ScreenId.CustomPlanScreen.name}/{field}",
            arguments = listOf(
                navArgument("field") {
                    type = NavType.StringType
                }
            )
        ) {
            val field = it.arguments?.getString("field") ?: ""
            CustomPlanScreen(navController = navController, field = field)
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
            val post = it.arguments?.getParcelable("post") ?: DefaultImpl.mappedPost

            DetailsScreen(navController = navController, postModel = post)
        }

        composable(ScreenId.NotificationScreen.name) {
            NotificationScreen()
        }



        composable(ScreenId.LastContactScreen.name) {
            LastContactScreen()
        }

        composable(ScreenId.SavedQuestionsScreen.name) {
            SavedQuestionsScreen(navController = navController)
        }

        composable(ScreenId.LikedPostsScreen.name) {
            LikedPostScreen(navController = navController)
        }

        composable(ScreenId.HelpScreen.name) {
            HelpScreen(navController)
        }

        composable(ScreenId.LanguageScreen.name) {
            LanguageScreen(navController = navController)
        }

        composable(ScreenId.VerifyAccountScreen.name) {
            VerifyAccountScreen(navController = navController)
        }

        composable(ScreenId.ResetPasswordScreen.name) {
            ResetPasswordScreen(navController = navController)
        }

        composable(ScreenId.ContactWithUsScreen.name) {
            CustomPlanScreen(navController = navController)
        }

    }
}