package com.natiqhaciyef.witapplication.presentation.screens.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.natiqhaciyef.util.common.Status
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.domain.domain.usecase.firebase.user.GetAllUsersUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.GetAllPostRemoteUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.util.models.top.UserAbstraction
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val firebaseAuth: FirebaseAuth,
    private val getAllPostRemoteUseCase: GetAllPostRemoteUseCase,
) : BaseViewModel() {
    private val _postState = mutableStateOf(BaseUIState<MappedPostModel>())
    val postState: MutableState<BaseUIState<MappedPostModel>>
        get() = _postState

    private val _userState =
        mutableStateOf<BaseUIState<UserWithoutPasswordModel>>(BaseUIState(isLoading = true))
    val userState: MutableState<BaseUIState<UserWithoutPasswordModel>>
        get() = _userState

    init {
        getAllUsers()
        getAllPosts()
    }

    private fun getAllUsers(
        onSuccess: (List<UserAbstraction>) -> Unit = {
            _userState.value = _userState.value.copy(
                data = if (it.isNotEmpty()) it[0] as UserWithoutPasswordModel else null,
                list = it as? List<UserWithoutPasswordModel> ?: listOf(),
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
        getAllUsersUseCase.invoke(
            onSuccess = onSuccess,
            onFail = onFail
        )
    }

    fun getAllPosts() {
        viewModelScope.launch {
            getAllPostRemoteUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        _postState.value = _postState.value.copy(
                            isLoading = true
                        )
                    }

                    Status.ERROR -> {
                        _postState.value = _postState.value.copy(
                            isFailMessage = result.message,
                            isLoading = false
                        )
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            _postState.value = _postState.value.copy(
                                list = result.data!!,
                                isLoading = false
                            )
                    }
                }
            }
        }
    }


    fun filterUsersByEmail(
        list: MutableList<UserWithoutPasswordModel>
    ) = firebaseAuth.currentUser?.let {
        list.filter { user -> user.email == it.email }
    }

    fun refreshData(count: MutableState<Int>) {
        postState.value.isLoading = true
        count.value += 1
        postState.value.isLoading = false
    }
}