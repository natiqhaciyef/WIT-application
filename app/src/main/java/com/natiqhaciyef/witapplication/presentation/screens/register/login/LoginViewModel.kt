package com.natiqhaciyef.witapplication.presentation.screens.register.login

import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.domain.domain.usecase.firebase.user.SignInUserUseCase
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase
) : BaseViewModel() {

    fun signIn(
        user: UserModel,
        onSuccess: () -> Unit = { },
        onFail: (Exception?) -> Unit = { }
    ) {
        viewModelScope.launch {
            signInUserUseCase.invoke(user, onSuccess, onFail)
        }
    }

}