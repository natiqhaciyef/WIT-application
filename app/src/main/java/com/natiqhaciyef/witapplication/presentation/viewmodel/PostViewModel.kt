package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.witapplication.common.Status
import com.natiqhaciyef.witapplication.common.util.helpers.getNow
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.models.UserWithoutPasswordModel
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel
import com.natiqhaciyef.witapplication.domain.usecase.local.post.GetAllSavedPostsUseCase
import com.natiqhaciyef.witapplication.domain.usecase.local.post.RemoveSavedPostUseCase
import com.natiqhaciyef.witapplication.domain.usecase.local.post.SavePostUseCase
import com.natiqhaciyef.witapplication.domain.usecase.local.post.UpdateSavedPostUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.post.GetAllPostRemoteUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.post.InsertPostRemoteUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.post.RemovePostRemoteUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.post.UpdatePostRemoteUseCase
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getAllPostRemoteUseCase: GetAllPostRemoteUseCase,
    private val insertPostRemoteUseCase: InsertPostRemoteUseCase,
    private val removePostRemoteUseCase: RemovePostRemoteUseCase,
    private val updatePostRemoteUseCase: UpdatePostRemoteUseCase,
    private val savePostUseCase: SavePostUseCase,
    private val getAllSavedPostsUseCase: GetAllSavedPostsUseCase,
    private val removeSavedPostUseCase: RemoveSavedPostUseCase,
    private val updateSavedPostUseCase: UpdateSavedPostUseCase,
) : BaseViewModel() {
    val postUIState = mutableStateOf(UIState<MappedPostModel>())
    val savedPostUIState = mutableStateOf(UIState<MappedPostModel>())

    init {
        getAllPosts()
        getAllSavedPosts()
//        insertPostRemote(
//            postModel = MappedPostModel(
//                id = 0,
//                title = "",
//                description = "The Jetpack Benchmark library provides a set of tools to inspect and improve application performance in a controlled environment.\n" +
//                        "\n" +
//                        "These lab tests can inspect entire end to end user flows using Macrobenchmark or implementation details in a loop with Microbenchmark.\n" +
//                        "\n" +
//                        "Macrobenchmark also can be used to generate baseline profiles and now Startup Profiles. Both help to improve application performance.\n" +
//                        "\n" +
//                        "All these features are provided by the androidx.benchmark library group. Despite the minor version increment from 1.1.1 to 1.2.0, a lot of new features have been added. Let’s dive into what’s new in Jetpack Benchmark 1.2.0.\n" +
//                        "\n" +
//                        "Baseline profiles improve overall code execution speed of included paths by about 30% by avoiding interpretation and the cost of class initialization by ahead of time compiling.\n" +
//                        "\n" +
//                        "These profiles have been around for a while and are already used by many apps and games on the Google Play Store. Libraries can also contribute baseline profiles and improve app performance seamlessly. And with the new Gradle plugin, automated baseline profile generation is easier than ever.",
//                image = "https://miro.medium.com/v2/resize:fit:2000/format:webp/1*Tebpw326SZ5SzhkXGPYJrA.png",
//                likeCount = 0,
//                location = null,
//                publishDate = getNow(),
//                user = UserWithoutPasswordModel(
//                    id = 1,
//                    name = "Natiq Haciyev",
//                    email = "natig.haciyef@gmail.com"
//                )
//            )
//        )
    }

    private fun getAllPosts() {
        viewModelScope.launch {
            getAllPostRemoteUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        postUIState.value.isLoading = true
                    }

                    Status.ERROR -> {
                        postUIState.value.apply {
                            message = result.message
                            isLoading = false
                        }
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postUIState.value.apply {
                                this.list = result.data
                                this.isLoading = false
                            }
                    }
                }
            }
        }
    }

    fun insertPostRemote(postModel: MappedPostModel) {
        viewModelScope.launch {
            insertPostRemoteUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        postUIState.value.isLoading = true
                    }

                    Status.ERROR -> {
                        postUIState.value.apply {
                            this.isLoading = false
                            this.message = result.message
                        }
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postUIState.value.apply {
                                this.message = result.data
                                this.isLoading = false
                            }
                    }
                }
            }
        }
    }


    fun removePostRemote(id: Int) {
        viewModelScope.launch {
            removePostRemoteUseCase.invoke(id).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        postUIState.value.isLoading = true
                    }

                    Status.ERROR -> {
                        postUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postUIState.value.apply {
                                message = result.data
                                isLoading = false
                            }
                    }
                }
            }
        }
    }


    fun updatePostRemote(
        postModel: MappedPostModel,
        onSuccess: () -> Unit = {},
    ) {
        viewModelScope.launch {
            updatePostRemoteUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        postUIState.value.isLoading = true
                    }

                    Status.ERROR -> {
                        postUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postUIState.value.apply {
                                isLoading = false
                                message = result.data
                            }

                        onSuccess()
                    }
                }
            }
        }
    }


    fun getAllSavedPosts() {
        viewModelScope.launch {
            getAllSavedPostsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            savedPostUIState.value.apply {
                                this.isLoading = false
                                this.list = result.data
                            }
                    }

                    Status.ERROR -> {
                        savedPostUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        savedPostUIState.value.isLoading = true
                    }
                }
            }
        }
    }


    fun removeSavedPost(postModel: MappedPostModel) {
        viewModelScope.launch {
            removeSavedPostUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        savedPostUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                    }

                    Status.ERROR -> {
                        savedPostUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        savedPostUIState.value.isLoading = true
                    }
                }
            }
        }
    }

    fun savePost(postModel: MappedPostModel) {
        viewModelScope.launch {
            savePostUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        savedPostUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                    }

                    Status.ERROR -> {
                        savedPostUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        savedPostUIState.value.isLoading = true
                    }
                }
            }
        }
    }

    fun updateSavedPost(postModel: MappedPostModel) {
        viewModelScope.launch {
            updateSavedPostUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        savedPostUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                    }

                    Status.ERROR -> {
                        savedPostUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        savedPostUIState.value.isLoading = true
                    }
                }
            }
        }
    }

    fun refresh(count: MutableState<Int>): MutableState<Int> {
        postUIState.value.isLoading = true
        count.value += 10
        postUIState.value.isLoading = false
        return count
    }
}