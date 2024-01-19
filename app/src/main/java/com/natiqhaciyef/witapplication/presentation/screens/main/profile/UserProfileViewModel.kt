package com.natiqhaciyef.witapplication.presentation.screens.main.profile

import com.natiqhaciyef.domain.domain.usecase.firebase.user.SignOutUserUseCase
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val signOutUserUseCase: SignOutUserUseCase
) : BaseViewModel() {

    fun signOut(
        onSuccess: () -> Unit = {},
        onFail: (Exception?) -> Unit = {}
    ) = signOutUserUseCase.invoke(onSuccess, onFail)

}