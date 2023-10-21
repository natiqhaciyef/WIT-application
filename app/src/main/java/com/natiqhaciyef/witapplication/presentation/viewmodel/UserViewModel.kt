package com.natiqhaciyef.witapplication.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
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

    fun clearPassword(
        userState: MutableState<UIState<UserModel>>,
        email: MutableState<String>,
    ) {
        val filterUser = userState.value.list.filter { it.email == email.value }
        if (filterUser.isNotEmpty()) {
            val customUser = filterUser[0]
            customUser.password = ""
            updateUser(customUser)
        }
    }

    fun changePassword(
        userState: MutableState<UIState<UserModel>>,
        email: String,
        password: String,
        onSuccess: () -> Unit = {}
    ) {
        val filteredUser = userState.value.list.filter { it.email == email }
        if (filteredUser.isNotEmpty()) {
            val customUser = filteredUser[0]
            customUser.password = password
            updateUser(customUser)
            onSuccess()
        }
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

    fun insertUser(userModel: UserModel, onSuccess: () -> Unit = {}) {
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


    fun userLoginCheckFromDB(
        userState: MutableState<UIState<UserModel>>,
        email: String,
        password: String,
        onSuccess: () -> Unit = { },
        onFail: () -> Unit = { }
    ) {
        if (userState.value.list.any { it.email == email && it.password == password }) {
            onSuccess()
        } else if (userState.value.list.any { it.email == email && it.password != password }) {
            // after reset password, updating password
            changePassword(
                userState = userState,
                email = email,
                password = password,
            ) {
                onSuccess()
            }
        } else {
            onFail()
        }
    }
}