package com.natiqhaciyef.witapplication.presentation.screens.register.sign_in

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.natiqhaciyef.witapplication.R

object SignInConfig {
    //    val signInRequest
    fun generateSignInRequest(context: Context) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build()
        ).build()
}