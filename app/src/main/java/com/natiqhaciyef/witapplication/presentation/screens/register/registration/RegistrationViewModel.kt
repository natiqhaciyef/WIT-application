package com.natiqhaciyef.witapplication.presentation.screens.register.registration

import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.domain.domain.usecase.firebase.user.CreateUserAccountUseCase
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val createUserAccountUseCase: CreateUserAccountUseCase
) : BaseViewModel() {

    fun createAccount(
        userModel: UserModel,
        onSuccess: () -> Unit = { },
        onFail: (Exception?) -> Unit = { }
    ) {
        viewModelScope.launch {
            createUserAccountUseCase.invoke(userModel, onSuccess, onFail)
        }
    }

}