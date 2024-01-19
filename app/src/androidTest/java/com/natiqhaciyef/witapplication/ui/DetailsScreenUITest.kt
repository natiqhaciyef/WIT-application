package com.natiqhaciyef.witapplication.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.MediumTest
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.natiqhaciyef.domain.domain.usecase.firebase.user.GetAllUsersUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.GetAllSavedPostsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.RemoveSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.SavePostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.UpdateSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.GetAllPostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.InsertPostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.RemovePostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.UpdatePostRemoteUseCase
import com.natiqhaciyef.util.common.mappers.toMappedPost
import com.natiqhaciyef.util.models.PostModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.witapplication.presentation.screens.main.details.DetailsScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.home.HomeViewModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.PostViewModel
import com.natiqhaciyef.witapplication.repository.FakeFirebaseRepository
import com.natiqhaciyef.witapplication.repository.FakePostRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
//@HiltAndroidApp
class DetailsScreenUITest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    private val postModel = PostModel(
        id = 0,
        title = "Adopt Compose for View based libraries",
        description = "Jetpack Compose is designed with View interoperability in mind. This means that existing View-based libraries can readily be used in Compose. However, considering how View-based libraries are used in Compose can improve support for Compose. For library authors, this can inform how you design your APIs, and further, how you may want to provide explicit support for Compose through additional libraries.",
        image = "https://miro.medium.com/v2/resize:fit:1100/format:webp/1*lQAfLPEsevYZmnYDBSqylQ.png",
        likeCount = 5,
        location = "null",
        publishDate = "18.10.2023 13:21",
        user = Gson().toJson(
            UserWithoutPasswordModel(
                id = "0",
                name = "Google",
                email = "google.com",
            )
        )
    )

    private lateinit var fakePostRepository: FakePostRepository
    private lateinit var fakeFirebaseRepository: FakeFirebaseRepository
    private lateinit var homeViewModel: HomeViewModel


    @Before
    fun setup() {
        fakePostRepository =
            FakePostRepository(
                mutableListOf(
                    postModel.copy(id = 1),
                    postModel.copy(id = 2),
                    postModel.copy(id = 3),
                )
            )

        homeViewModel = HomeViewModel(
            getAllPostRemoteUseCase = GetAllPostRemoteUseCase(fakePostRepository),
            getAllUsersUseCase = GetAllUsersUseCase(fakeFirebaseRepository),
            firebaseAuth = FirebaseAuth.getInstance()
        )

    }


    @Test
    fun detailsScreenLikeButtonTestReturnsSuccess() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            DetailsScreen(
                navController = navController,
                postModel = postModel.toMappedPost()!!,
                homeViewModel = homeViewModel
            )
        }

        val button = composeTestRule
            .onNode(hasTestTag("Detail screen like button test tag"), true)

        button.assertIsDisplayed()
        button.performClick()
    }

    @Test
    fun detailsScreenImageTestReturnsSuccess() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            DetailsScreen(
                navController = navController,
                postModel = postModel.toMappedPost()!!,
                homeViewModel = homeViewModel
            )
        }

        val image = composeTestRule
            .onNode(hasTestTag("Details screen image test tag"), true)

        image.assertIsDisplayed()
    }

    @Test
    fun detailsScreenTitleTextTestReturnsSuccess() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            DetailsScreen(
                navController = navController,
                postModel = postModel.toMappedPost()!!,
                homeViewModel = homeViewModel
            )
        }

        val titleText = composeTestRule
            .onNode(hasTestTag("Details screen title text test tag"), true)

        titleText.assertTextEquals(postModel.title)
        titleText.assertIsDisplayed()
    }
}