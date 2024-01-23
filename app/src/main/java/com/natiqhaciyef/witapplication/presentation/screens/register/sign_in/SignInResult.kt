package com.natiqhaciyef.witapplication.presentation.screens.register.sign_in

import com.natiqhaciyef.util.models.UserGoogleSignInModel


data class SignInResult(
    val data: UserGoogleSignInModel?,
    val errorMessage: String?
)

