package com.natiqhaciyef.witapplication.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.MediumTest
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.presentation.navigation.TestNavigation
import com.natiqhaciyef.witapplication.presentation.screens.main.user.UserProfileMainView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
@ExperimentalCoroutinesApi
class UserProfileScreenUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun userProfileScreenFieldOnClickNavigationTestReturnsSuccess() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            TestNavigation(navController)
            val openDialog = remember { mutableStateOf(false) }
            UserProfileMainView(navController = navController, openDialog = openDialog)
        }

        val field = composeTestRule.onNode(hasTestTag("User Profile field test tag"), true)
        field.performClick()
        field.assertIsDisplayed()
        field.assertHasClickAction()
        field.assert(hasTestTag("User Profile field test tag")) {
            ErrorMessages.EMPTY_FIELD
        }
    }
}