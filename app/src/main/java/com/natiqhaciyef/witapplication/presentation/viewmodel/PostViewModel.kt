package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.voyagersaz.common.Status
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel
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
) : BaseViewModel() {
    val postUIState = mutableStateOf(UIState<MappedPostModel>())

    init {
        getAllPosts()
    }

    private fun getAllPosts() {
        viewModelScope.launch {
            getAllPostRemoteUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        postUIState.value = postUIState.value.copy(isLoading = true)
                    }

                    Status.ERROR -> {
                        postUIState.value =
                            postUIState.value.copy(isLoading = false, message = result.message)
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postUIState.value =
                                postUIState.value.copy(isLoading = false, list = result.data)
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
                        postUIState.value = postUIState.value.copy(isLoading = true)
                    }

                    Status.ERROR -> {
                        postUIState.value =
                            postUIState.value.copy(isLoading = false, message = result.message)
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postUIState.value =
                                postUIState.value.copy(isLoading = false, message = result.data.message)
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
                        postUIState.value = postUIState.value.copy(isLoading = true)
                    }

                    Status.ERROR -> {
                        postUIState.value =
                            postUIState.value.copy(isLoading = false, message = result.message)
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postUIState.value =
                                postUIState.value.copy(isLoading = false, message = result.data.message)
                    }
                }
            }
        }
    }


    fun updatePostRemote(postModel: MappedPostModel) {
        viewModelScope.launch {
            updatePostRemoteUseCase.invoke(postModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        postUIState.value = postUIState.value.copy(isLoading = true)
                    }

                    Status.ERROR -> {
                        postUIState.value =
                            postUIState.value.copy(isLoading = false, message = result.message)
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            postUIState.value =
                                postUIState.value.copy(isLoading = false, message = result.data.message)
                    }
                }
            }
        }
    }

}