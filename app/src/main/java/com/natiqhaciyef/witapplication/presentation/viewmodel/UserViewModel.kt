package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.natiqhaciyef.witapplication.common.Status
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.domain.usecase.remote.user.GetAllUserRemoteUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.user.GetUserByEmailUseCase
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
    private val firebaseAuth: FirebaseAuth,
    private val getUserByEmailUseCase: GetUserByEmailUseCase,
    private val insertUserRemoteUseCase: InsertUserRemoteUseCase,
    private val removeUserRemoteUseCase: RemoveUserRemoteUseCase,
    private val updateUserRemoteUseCase: UpdateUserRemoteUseCase,
) : BaseViewModel() {
    val userUIState = mutableStateOf(UIState<UserModel>())

    init {
        getUserByEmail()
    }

    fun getUserByEmail(email: String = "") {
        viewModelScope.launch {
            if (FirebaseAuth.getInstance().currentUser != null && email.isEmpty()) {
                getUserByEmailUseCase.invoke(FirebaseAuth.getInstance().currentUser?.email.toString())
                    .collectLatest { result ->
                        when (result.status) {
                            Status.SUCCESS -> {
                                if (result.data != null)
                                    userUIState.value.apply {
                                        this.list = result.data
                                        this.isLoading = false
                                        this.selectedElement = result.data[0]
                                    }
                            }

                            Status.ERROR -> {
                                userUIState.value.apply {
                                    this.message = result.message
                                    this.isLoading = false
                                }
                            }

                            Status.LOADING -> {
                                userUIState.value.isLoading = true
                            }
                        }
                    }
            } else {
                getUserByEmailUseCase.invoke(email)
                    .collectLatest { result ->
                        when (result.status) {
                            Status.SUCCESS -> {
                                if (result.data != null)
                                    userUIState.value.apply {
                                        this.list = result.data
                                        this.isLoading = false
                                        this.selectedElement = result.data[0]
                                    }
                            }

                            Status.ERROR -> {
                                userUIState.value.apply {
                                    this.message = result.message
                                    this.isLoading = false
                                }
                            }

                            Status.LOADING -> {
                                userUIState.value.isLoading = true
                            }
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
                            userUIState.value.apply {
                                this.message = result.data
                                this.isLoading = false
                            }
                    }

                    Status.ERROR -> {
                        userUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        userUIState.value.isLoading = true
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
                            userUIState.value.apply {
                                this.message = result.data
                                this.isLoading = false
                            }
                    }

                    Status.ERROR -> {
                        userUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        userUIState.value.isLoading = true
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
                            userUIState.value.apply {
                                this.message = result.data
                                this.isLoading = false
                            }
                    }

                    Status.ERROR -> {
                        userUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        userUIState.value.isLoading = true
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
        onFail: () -> Unit = { },
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
        onSuccess: () -> Unit = {},
    ) {
        val filteredUser = userState.value.list.filter { it.email == email }
        if (filteredUser.isNotEmpty()) {
            val customUser = filteredUser[0]
            customUser.password = password
            updateUser(customUser)
            onSuccess()
        }
    }

    fun getUser(users: List<UserModel>) {
        viewModelScope.launch {
            val auth = firebaseAuth.currentUser
            val result = users.filter { it.email == auth?.email }
            if (result.isNotEmpty())
                userUIState.value = userUIState.value.copy(selectedElement = result[0])
            else
                userUIState.value = userUIState.value.copy(selectedElement = null)
        }
    }
}