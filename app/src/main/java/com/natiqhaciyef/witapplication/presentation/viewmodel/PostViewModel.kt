package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.common.Status
import com.natiqhaciyef.domain.domain.usecase.local.post.GetAllSavedPostsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.RemoveSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.SavePostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.UpdateSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.GetAllPostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.InsertPostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.RemovePostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.UpdatePostRemoteUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val insertPostRemoteUseCase: InsertPostRemoteUseCase,
    private val removePostRemoteUseCase: RemovePostRemoteUseCase,
) : BaseViewModel() {
    val postBaseUIState = mutableStateOf(BaseUIState<MappedPostModel>())
    val savedPostBaseUIState = mutableStateOf(BaseUIState<MappedPostModel>())

    init {
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


    fun insertPostRemote(postModel: MappedPostModel) {
        viewModelScope.launch {
            insertPostRemoteUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        postBaseUIState.value = postBaseUIState.value.copy(
                            isLoading = true
                        )
                    }

                    Status.ERROR -> {
                        postBaseUIState.value = postBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = false,
                            isFail = true,
                            isSuccessMessage = null,
                            isFailMessage = ErrorMessages.ERROR,
                            isFailReason = Exception(result.message),
                        )
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postBaseUIState.value = postBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = result.data,
                                isFailMessage = null,
                                isFailReason = null,
                            )
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
                        postBaseUIState.value = postBaseUIState.value.copy(
                            isLoading = true
                        )
                    }

                    Status.ERROR -> {
                        postBaseUIState.value = postBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = false,
                            isFail = true,
                            isSuccessMessage = null,
                            isFailMessage = ErrorMessages.ERROR,
                            isFailReason = Exception(result.message),
                        )
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postBaseUIState.value = postBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = result.data,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }
                }
            }
        }
    }

}