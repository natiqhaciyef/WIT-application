package com.natiqhaciyef.witapplication.presentation.screens.register.sign_in

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

) : BaseViewModel() {
    private val _googleSignInState = MutableStateFlow(SignInState())
    val googleSignInState = _googleSignInState.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _googleSignInState.update {
            it.copy(
                isSignedInSuccessful = result.data != null,
                signedInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _googleSignInState.update { it.copy() }
    }
}