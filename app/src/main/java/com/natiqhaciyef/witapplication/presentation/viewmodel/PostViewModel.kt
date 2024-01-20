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
import com.natiqhaciyef.util.common.util.helpers.getNow
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
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

//    init {
//        insertPostRemote(
//            postModel = MappedPostModel(
//                id = 0,
//                title = "Now in Android",
//                description = "On December 6th , we announced Gemini, our most capable AI model yet!\n" +
//                        "It’s been optimized for three different sizes: Ultra, Pro and Nano. Gemini Nano is our most efficient model built for on-device tasks, running directly on mobile silicon which opens support for offline, privacy-sensitive inference.\n" +
//                        "\n" +
//                        "We announced that you will soon be able to use the Google AI Edge SDK to leverage Gemini Nano through Android AICore. It is currently rolling out to Pixel 8 Pro with more devices and silicon partners to be announced in the coming months.\n" +
//                        "\n" +
//                        "AICore is currently being used to summarize content in the Recorder app, even when the phone’s network connection is offline.\n" +
//                        "\n" +
//                        "If you are interested in building apps using Gemini Nano and Google AI Edge SDK, please sign up for our Early Access Program.\n" +
//                        "\n" +
//                        "The new ML Kit Subject Segmentation API, now in beta, lets you easily separate multiple subjects from the background in a picture. The API takes an input image and generates an output mask or bitmap for the foreground. It also provides a mask and bitmap for each one of the subjects detected.\n" +
//                        "\n" +
//                        "ML Kit Subject Segmentation API \uD83E\uDDE0" +
//                        "\n" +
//                        "It enables cool use cases such as background swap, or add unique effects to people, pets, or objects with just a few lines of code.",
//                image = "https://miro.medium.com/v2/resize:fit:2000/format:webp/1*aIux1an8zPUXIhTLY7psfw.png",
//                likeCount = 0,
//                location = null,
//                publishDate = getNow() ?: DefaultImpl.mappedPost.publishDate,
//                user = UserWithoutPasswordModel(
//                    id = "1",
//                    name = "Natiq Haciyev",
//                    email = "natig.haciyef@gmail.com"
//                )
//            )
//        )
//    }


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