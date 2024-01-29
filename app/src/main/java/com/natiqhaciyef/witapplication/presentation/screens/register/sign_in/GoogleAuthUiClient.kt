package com.natiqhaciyef.witapplication.presentation.screens.register.sign_in

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.witapplication.R
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.default_web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }

    companion object {
        fun generateGoogleAuthUiClient(context: Context): GoogleAuthUiClient {
            return GoogleAuthUiClient(context, Identity.getSignInClient(context))
        }

        fun handleSignInResult(
            result: ActivityResult,
            onSuccess: () -> Unit = {},
            onFail: (Exception?) -> Unit = {
                println(it?.localizedMessage)
            }
        ) {
            GoogleSignIn.getSignedInAccountFromIntent(result.data).addOnSuccessListener {
                handleSuccessfulSignIn(account = it, onSuccess = onSuccess)
            }.addOnFailureListener {
                onFail(it)
            }
        }

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
                    .build()
            ).build()
    }
}
