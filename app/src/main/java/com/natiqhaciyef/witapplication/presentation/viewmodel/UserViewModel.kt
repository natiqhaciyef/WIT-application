package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.voyagersaz.common.Status
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.domain.usecase.remote.user.GetAllUserRemoteUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.user.InsertUserRemoteUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.user.RemoveUserRemoteUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.user.UpdateUserRemoteUseCase
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getAllUserRemoteUseCase: GetAllUserRemoteUseCase,
    private val insertUserRemoteUseCase: InsertUserRemoteUseCase,
    private val removeUserRemoteUseCase: RemoveUserRemoteUseCase,
    private val updateUserRemoteUseCase: UpdateUserRemoteUseCase,
) : BaseViewModel() {
    val userUIState = mutableStateOf(UIState<UserModel>())

    init {
        getAllUser()
    }

    private fun getAllUser() {
        viewModelScope.launch {
            getAllUserRemoteUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            userUIState.value =
                                userUIState.value.copy(list = result.data, isLoading = false)
                    }

                    Status.ERROR -> {
                        userUIState.value =
                            userUIState.value.copy(message = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        userUIState.value = userUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun insertUser(userModel: UserModel) {
        viewModelScope.launch {
            insertUserRemoteUseCase.invoke(userModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            userUIState.value = userUIState.value.copy(
                                message = result.data.message,
                                isLoading = false
                            )
                    }

                    Status.ERROR -> {
                        userUIState.value =
                            userUIState.value.copy(message = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        userUIState.value = userUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun removeUser(id: Int) {
        viewModelScope.launch {
            removeUserRemoteUseCase.invoke(id).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            userUIState.value =
                                userUIState.value.copy(
                                    message = result.data.message,
                                    isLoading = false
                                )
                    }

                    Status.ERROR -> {
                        userUIState.value =
                            userUIState.value.copy(message = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        userUIState.value = userUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }


    fun updateUser(userModel: UserModel) {
        viewModelScope.launch {
            updateUserRemoteUseCase.invoke(userModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            userUIState.value =
                                userUIState.value.copy(
                                    message = result.data.message,
                                    isLoading = false
                                )
                    }

                    Status.ERROR -> {
                        userUIState.value =
                            userUIState.value.copy(message = result.message, isLoading = false)
                    }

                    Status.LOADING -> {
                        userUIState.value = userUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

}