package com.natiqhaciyef.witapplication.presentation.screens.register.sign_in

import android.content.Context
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.witapplication.R

object GoogleAuthUiClient {
    fun handleSignInResultForGoogleAuth(
        result: ActivityResult,
        onSuccess: () -> Unit = {},
        onFail: (Exception?) -> Unit = {
            println(it?.localizedMessage)
        }
    ) {
        GoogleSignIn.getSignedInAccountFromIntent(result.data)
            .addOnSuccessListener {
                handleSuccessfulSignIn(account = it, onSuccess = onSuccess)
            }.addOnFailureListener {
                onFail(it)
            }
    }

    private fun handleSuccessfulSignIn(
        account: GoogleSignInAccount,
        onSuccess: () -> Unit = {},
        onFail: () -> Unit = {}
    ) {
        val idToken = account.idToken ?: return
        Firebase.auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
            .addOnSuccessListener {
                updateCurrentUserInApp(it.user)
                onSuccess()
            }
            .addOnFailureListener {
                onFail()
                println(it)
            }
    }

    private fun updateCurrentUserInApp(user: FirebaseUser?) {
        if (user != null) {
            // Update local data, handle UI changes
            Firebase.auth.updateCurrentUser(user).addOnSuccessListener {
                println("Success")
            }.addOnFailureListener {
                println("Fail")
            }
        } else {
            println("Fail 2 null")
        }
    }

    fun generateSignInRequest(context: Context) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build()).build()
}