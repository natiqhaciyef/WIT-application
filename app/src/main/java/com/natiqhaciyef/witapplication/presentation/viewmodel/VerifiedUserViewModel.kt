package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.natiqhaciyef.witapplication.common.Status
import com.natiqhaciyef.witapplication.data.models.VerifiedUserModel
import com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user.GetAllVerifiedUsersUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user.GetVerifiedUserByEmailUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user.InsertVerifiedUserUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user.RemoveVerifiedUserUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user.UpdateVerifiedUserUseCase
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VerifiedUserViewModel @Inject constructor(
    private val getVerifiedUserByEmailUseCase: GetVerifiedUserByEmailUseCase,
    private val removeVerifiedUserUseCase: RemoveVerifiedUserUseCase,
) : BaseViewModel() {
    val verifiedUserUIState = mutableStateOf(UIState<VerifiedUserModel>())

    init {
        getVerifiedUserByEmail()
    }

    fun getVerifiedUserByEmail(email: String = "") {
        viewModelScope.launch {
            if (email.isEmpty() && FirebaseAuth.getInstance().currentUser != null) {
                getVerifiedUserByEmailUseCase.invoke(FirebaseAuth.getInstance().currentUser?.email.toString())
                    .collectLatest { result ->
                        when (result.status) {
                            Status.SUCCESS -> {
                                if (result.data != null)
                                    verifiedUserUIState.value = verifiedUserUIState.value.copy(
                                        list = result.data,
                                        isLoading = false,
                                        selectedElement = result.data[0],

                                        )
                            }

                            Status.ERROR -> {
                                verifiedUserUIState.value = verifiedUserUIState.value.copy(
                                    message = result.message,
                                    isLoading = false
                                )
                            }

                            Status.LOADING -> {
                                verifiedUserUIState.value =
                                    verifiedUserUIState.value.copy(isLoading = true)
                            }
                        }
                    }
            } else {
                getVerifiedUserByEmailUseCase.invoke(email)
                    .collectLatest { result ->
                        when (result.status) {
                            Status.SUCCESS -> {
                                if (result.data != null)
                                    verifiedUserUIState.value = verifiedUserUIState.value.copy(
                                        list = result.data,
                                        isLoading = false,
                                        selectedElement = result.data[0],

                                        )
                            }

                            Status.ERROR -> {
                                verifiedUserUIState.value = verifiedUserUIState.value.copy(
                                    message = result.message,
                                    isLoading = false
                                )
                            }

                            Status.LOADING -> {
                                verifiedUserUIState.value =
                                    verifiedUserUIState.value.copy(isLoading = true)
                            }
                        }
                    }
            }
        }
    }

    fun removeVerifiedUser(id: Int) {
        viewModelScope.launch {
            removeVerifiedUserUseCase.invoke(id).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            verifiedUserUIState.value = verifiedUserUIState.value.copy(
                                message = result.data,
                                isLoading = false
                            )
                    }

                    Status.ERROR -> {
                        verifiedUserUIState.value = verifiedUserUIState.value.copy(
                            message = result.message,
                            isLoading = false
                        )
                    }

                    Status.LOADING -> {
                        verifiedUserUIState.value =
                            verifiedUserUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}