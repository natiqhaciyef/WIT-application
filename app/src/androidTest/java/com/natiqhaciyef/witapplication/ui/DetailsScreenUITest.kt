package com.natiqhaciyef.witapplication.ui

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.MediumTest
import com.natiqhaciyef.domain.domain.usecase.local.post.GetAllSavedPostsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.RemoveSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.SavePostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.UpdateSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.GetAllPostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.InsertPostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.RemovePostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.UpdatePostRemoteUseCase
import com.natiqhaciyef.util.common.mappers.toPost
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.witapplication.presentation.screens.main.home.DetailsScreen
import com.natiqhaciyef.witapplication.presentation.viewmodel.PostViewModel
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

    private lateinit var postViewModel: PostViewModel
    private lateinit var fakePostRepository: FakePostRepository


    @Before
    fun setup() {
        fakePostRepository =
            FakePostRepository(mutableListOf(DefaultImpl.mappedPost.toPost()!!.copy(id = 1)))
        postViewModel = PostViewModel(
            getAllPostRemoteUseCase = GetAllPostRemoteUseCase(fakePostRepository),
            insertPostRemoteUseCase = InsertPostRemoteUseCase(fakePostRepository),
            removePostRemoteUseCase = RemovePostRemoteUseCase(fakePostRepository),
            updatePostRemoteUseCase = UpdatePostRemoteUseCase(fakePostRepository),
            savePostUseCase = SavePostUseCase(fakePostRepository),
            removeSavedPostUseCase = RemoveSavedPostUseCase(fakePostRepository),
            getAllSavedPostsUseCase = GetAllSavedPostsUseCase(fakePostRepository),
            updateSavedPostUseCase = UpdateSavedPostUseCase(fakePostRepository)
        )
    }

    @Test
    fun `registerButtonNavigationTest`() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            DetailsScreen(
                navController = navController,
                postModel = DefaultImpl.mappedPost,
                postViewModel = postViewModel
            )
        }
        val button = composeTestRule.onNode(hasTestTag("Detail screen like button test tag"), true)
        button.performClick()
    }
}