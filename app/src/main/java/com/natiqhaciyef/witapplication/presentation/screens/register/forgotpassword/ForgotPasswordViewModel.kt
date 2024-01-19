package com.natiqhaciyef.witapplication.presentation.screens.register.forgotpassword

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.natiqhaciyef.domain.domain.usecase.firebase.user.ChangeAccountPasswordUseCase
import com.natiqhaciyef.domain.domain.usecase.firebase.user.GetUserByEmailUseCase
import com.natiqhaciyef.domain.domain.usecase.firebase.user.ResetPasswordUserUseCase
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val resetPasswordUserUseCase: ResetPasswordUserUseCase,
    private val firebaseAuth: FirebaseAuth,
    private val getUserByEmailUseCase: GetUserByEmailUseCase,
    private val changeAccountPasswordUseCase: ChangeAccountPasswordUseCase
) : BaseViewModel() {

    fun resetPasswordByLink(
        email: String,
        onSuccess: () -> Unit = { },
        onFail: (Exception?) -> Unit = { }
    ) {
        viewModelScope.launch {
            resetPasswordUserUseCase.invoke(email, onSuccess, onFail)
        }
    }


    fun changePassword(
        userModel: UserModel,
        onSuccess: () -> Unit = { },
        onFail: (Exception?) -> Unit = { }
    ) {
        viewModelScope.launch {
            changeAccountPasswordUseCase.invoke(userModel, onSuccess, onFail)
        }
    }

    fun checkEmail(email: String) = firebaseAuth.currentUser?.email == email

    fun getUserByEmail(email: String): UserModel = getUserByEmailUseCase.invoke(email)
}