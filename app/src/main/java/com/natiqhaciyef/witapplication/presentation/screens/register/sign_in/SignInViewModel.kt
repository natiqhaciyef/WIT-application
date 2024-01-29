package com.natiqhaciyef.witapplication.presentation.screens.register.sign_in

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    fun generateGoogleSignInOptions(clientId: String) =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

    fun generateGoogleSignInClient(context: Context, gso: GoogleSignInOptions) =
        GoogleSignIn.getClient(context as ComponentActivity, gso)

    fun googleSignInResultHandler(
        task: Task<GoogleSignInAccount>,
        onSuccessAction: () -> Unit = { },
    ) {
        val account: GoogleSignInAccount? = task.result

        if (account != null) {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener {
                    if (task.isSuccessful) {
                        onSuccessAction()
                    } else {
                        println("Something went wrong!")
                    }
                }
        }
    }
}