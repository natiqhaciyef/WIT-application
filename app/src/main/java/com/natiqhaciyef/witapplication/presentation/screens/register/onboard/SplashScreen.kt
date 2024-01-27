package com.natiqhaciyef.witapplication.presentation.screens.register.onboard

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.screens.register.sign_in.GoogleAuthUiClient
import com.natiqhaciyef.witapplication.presentation.screens.register.sign_in.SignInViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    navController: NavController,
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val state by signInViewModel.googleSignInState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.isSignedInSuccessful) {
        if (state.isSignedInSuccessful) {
            Toast.makeText(
                context,
                "Sign in successful",
                Toast.LENGTH_LONG
            ).show()

            navController.navigate(ScreenId.MainScreenLine.name)
            signInViewModel.resetState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.wit_icon),
            contentDescription = "Voyagers image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(bottom = 50.dp)
                .size(300.dp)
                .align(Alignment.Center)
        )

        coroutineScope.launch(Dispatchers.Main) {
            delay(1500)
            if (FirebaseAuth.getInstance().currentUser != null) {
                navController.navigate(ScreenId.MainScreenLine.name) {
                    navController.popBackStack(ScreenId.SplashScreen.name, inclusive = true)
                }
//            } else if () {
            } else {
                navController.navigate(ScreenId.LoginScreen.name) {
                    navController.popBackStack(ScreenId.SplashScreen.name, inclusive = true)
                }
            }
        }
    }
}