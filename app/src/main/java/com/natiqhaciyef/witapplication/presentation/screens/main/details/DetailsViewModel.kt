package com.natiqhaciyef.witapplication.presentation.screens.main.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.common.Status
import com.natiqhaciyef.domain.domain.usecase.local.post.GetAllSavedPostsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.RemoveSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.SavePostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.UpdateSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.UpdatePostRemoteUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.util.models.PostModel
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import com.natiqhaciyef.witapplication.presentation.screens.main.home.HomeViewModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.PostViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val updatePostRemoteUseCase: UpdatePostRemoteUseCase,
    private val savePostUseCase: SavePostUseCase,
    private val getAllSavedPostsUseCase: GetAllSavedPostsUseCase,
    private val removeSavedPostUseCase: RemoveSavedPostUseCase,
    private val updateSavedPostUseCase: UpdateSavedPostUseCase,
) : BaseViewModel() {
    val postBaseUIState = mutableStateOf(BaseUIState<MappedPostModel>())
    val savedPostBaseUIState = mutableStateOf(BaseUIState<MappedPostModel>())
    var postModel: MappedPostModel? = null
    var homeViewModel: HomeViewModel? = null

    fun likeButton(isContains: Boolean) {
        if (postModel != null && homeViewModel != null)
            if (isContains) {
                postModel!!.likeCount += 1
                savePost(postModel!!)
                updatePostRemote(postModel!!) {
                    updateSavedPost(postModel!!)
                }
            } else {
                postModel!!.likeCount -= 1
                updatePostRemote(postModel!!) {
                    updateSavedPost(postModel!!) {
                        println(postModel!!.likeCount)
                        removeSavedPost(postModel!!)
                    }
                }
            }

        homeViewModel?.getAllPosts()
        getAllSavedPosts()
    }

    fun updatePostRemote(
        postModel: MappedPostModel,
        onFail: () -> Unit = {},
        onSuccess: () -> Unit = {},
    ) {
        viewModelScope.launch {
            updatePostRemoteUseCase.invoke(postModel).collectLatest { result ->
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
                        onFail()
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
                        onSuccess()
                    }
                }
            }
        }
    }

    fun removeSavedPost(postModel: MappedPostModel) {
        viewModelScope.launch {
            removeSavedPostUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
                            isLoading = true
                        )
                    }

                    Status.ERROR -> {
                        savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
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
                            savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
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


    fun savePost(postModel: MappedPostModel) {
        viewModelScope.launch {
            savePostUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
                            isLoading = true
                        )
                    }

                    Status.ERROR -> {
                        savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
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
                            savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
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

    fun updateSavedPost(postModel: MappedPostModel, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            updateSavedPostUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
                            isLoading = true
                        )
                    }

                    Status.ERROR -> {
                        savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
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
                            savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = result.data,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                        onSuccess()
                    }
                }
            }
        }
    }

    private fun getAllSavedPosts() {
        viewModelScope.launch {
            getAllSavedPostsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
                            isLoading = true
                        )
                    }

                    Status.ERROR -> {
                        savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
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
                            savedPostBaseUIState.value = savedPostBaseUIState.value.copy(
                                data = null,
                                list = result.data ?: listOf(),
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = SuccessMessages.SUCCESS,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                }
            }
        }
    }
}