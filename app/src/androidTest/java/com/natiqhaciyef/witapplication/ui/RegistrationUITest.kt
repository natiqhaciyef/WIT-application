package com.natiqhaciyef.witapplication.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.natiqhaciyef.witapplication.presentation.navigation.AppNavigation
import com.natiqhaciyef.witapplication.presentation.screens.register.RegisterScreen
import com.natiqhaciyef.witapplication.util.launchFragmentInHiltContainer
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
//@HiltAndroidApp
class RegistrationUITest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `registerButtonNavigationTest`() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            RegisterScreen(navController = navController)
        }


    }

}