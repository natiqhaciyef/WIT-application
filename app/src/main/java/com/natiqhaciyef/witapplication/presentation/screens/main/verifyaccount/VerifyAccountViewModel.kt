package com.natiqhaciyef.witapplication.presentation.screens.main.verifyaccount

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.domain.domain.base.USER
import com.natiqhaciyef.domain.domain.usecase.firebase.user.GetAllUsersUseCase
import com.natiqhaciyef.domain.domain.usecase.firebase.verified_user.CreateVerifiedUserUseCase
import com.natiqhaciyef.domain.domain.usecase.firebase.verified_user.GetAllVerifiedUsersUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.util.models.VerifiedUserWithoutPasswordModel
import com.natiqhaciyef.util.models.top.UserAbstraction
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyAccountViewModel @Inject constructor(
    private val getAllVerifiedUsersUseCase: GetAllVerifiedUsersUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val createVerifiedUserUseCase: CreateVerifiedUserUseCase
) : BaseViewModel() {
    private val _verifiedUserState =
        mutableStateOf<BaseUIState<VerifiedUserWithoutPasswordModel>>(BaseUIState())
    val verifiedUserState: MutableState<BaseUIState<VerifiedUserWithoutPasswordModel>>
        get() = _verifiedUserState

    private val _userState =
        mutableStateOf<BaseUIState<UserModel>>(BaseUIState())
    val userState: MutableState<BaseUIState<UserModel>>
        get() = _userState


    init {
        getAllVerifiedUsers()
        getAllUsers()
    }

    private fun getAllVerifiedUsers(
        onSuccess: (List<VerifiedUserWithoutPasswordModel>) -> Unit = {
            _verifiedUserState.value = _verifiedUserState.value.copy(
                data = null,
                list = it,
                isLoading = false,
                isSuccess = true,
                isFail = false,
                isSuccessMessage = SuccessMessages.SUCCESS,
                isFailMessage = null,
                isFailReason = null,
            )
        },
        onFail: (Exception?) -> Unit = {
            _verifiedUserState.value = _verifiedUserState.value.copy(
                data = null,
                list = listOf(),
                isLoading = false,
                isSuccess = false,
                isFail = true,
                isSuccessMessage = null,
                isFailMessage = ErrorMessages.ERROR,
                isFailReason = it,
            )
        }
    ) {
        viewModelScope.launch {
            getAllVerifiedUsersUseCase.invoke(onSuccess, onFail)
        }
    }

    private fun getAllUsers(
        onSuccess: (List<UserAbstraction>) -> Unit = {
            _userState.value = _userState.value.copy(
                data = null,
                list = it as? List<UserModel> ?: listOf(),
                isLoading = false,
                isSuccess = true,
                isFail = false,
                isSuccessMessage = SuccessMessages.SUCCESS,
                isFailMessage = null,
                isFailReason = null,
            )
        },
        onFail: (Exception?) -> Unit = {
            _userState.value = _userState.value.copy(
                data = null,
                list = listOf(),
                isLoading = false,
                isSuccess = false,
                isFail = true,
                isSuccessMessage = null,
                isFailMessage = ErrorMessages.ERROR,
                isFailReason = it,
            )
        }
    ) {
        viewModelScope.launch {
            getAllUsersUseCase.invoke(USER, onSuccess, onFail)
        }
    }

    fun insertVerifiedUser(
        verifiedUserModel: VerifiedUserModel,
        onSuccess: () -> Unit = {},
        onFail: (Exception?) -> Unit = {}
    ) {
        viewModelScope.launch {
            createVerifiedUserUseCase.invoke(verifiedUserModel, onSuccess, onFail)
        }
    }
}