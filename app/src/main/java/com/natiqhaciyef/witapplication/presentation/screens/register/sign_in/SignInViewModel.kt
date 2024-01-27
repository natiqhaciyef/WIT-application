package com.natiqhaciyef.witapplication.presentation.screens.register.sign_in

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : BaseViewModel() {
    val auth = firebaseAuth
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

    fun onSignOutResult() {
        _googleSignInState.update {
            it.copy(
                isSignedInSuccessful = false,
                signedInError = null
            )
        }
    }

    fun resetState() {
        _googleSignInState.update { it.copy() }
    }
}