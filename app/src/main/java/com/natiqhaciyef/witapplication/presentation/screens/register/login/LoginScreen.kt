package com.natiqhaciyef.witapplication.presentation.screens.register.login

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.component.BottomShadow
import com.natiqhaciyef.witapplication.presentation.component.CustomAlertDialogSample
import com.natiqhaciyef.witapplication.presentation.component.InputBox
import com.natiqhaciyef.witapplication.presentation.component.InputBoxPassword
import com.natiqhaciyef.witapplication.presentation.component.fonts.Lobster
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.screens.register.sign_in.SignInViewModel
import com.natiqhaciyef.witapplication.ui.theme.*


@Composable
fun LoginScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.ellipse_design_5),
            contentDescription = "Ellipse 1",
            modifier = Modifier
                .fillMaxWidth(0.45f)
                .align(Alignment.TopStart)
        )

        Icon(
            painter = painterResource(id = R.drawable.ellipse_design_2),
            contentDescription = "Ellipse 1",
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .align(Alignment.BottomEnd),
            tint = AppBrown
        )

        Column(
            modifier = Modifier.background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginTopView()
            LoginMainPart(navController = navController)
        }
    }
}


@Composable
private fun LoginTopView() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.login_animation),
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Crop
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(color = AppDarkBlue)
                ) {
                    append(stringResource(id = R.string.login_n_share))
                }

                withStyle(
                    SpanStyle(
                        color = AppBrown
                    )
                ) {
                    append(stringResource(id = R.string.posts))
                }
            },
            fontSize = 25.sp,
            fontFamily = Lobster.lobster,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
}


@Composable
private fun LoginMainPart(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val isAlertDialogOpenState = remember { mutableStateOf(false) }
    val errorAvailable = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        backgroundColor = AppDarkBlue
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            InputBox(
                concept = stringResource(id = R.string.email),
                input = emailState,
                isSingleLine = true,
                leadingIcon = Icons.Default.Email
            )
            Spacer(modifier = Modifier.height(8.dp))

            InputBoxPassword(
                concept = stringResource(id = R.string.password),
                input = passwordState,
                passVisibility = passwordVisible,
                isSingleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = stringResource(id = R.string.forgot_password),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .clickable {
                        navController.navigate(ScreenId.ForgotPasswordScreen.name)
                    },
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(45.dp))
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                onClick = {
                    val email = emailState.value
                    val password = passwordState.value

                    val user = UserModel("-", " ", email, password)
                    loginViewModel.signIn(
                        user = user,
                        onSuccess = {
                            errorAvailable.value = ""
                            navController.navigate(ScreenId.MainScreenLine.name) {
                                navController.popBackStack(
                                    ScreenId.LoginScreen.name,
                                    inclusive = true
                                )
                            }
                        },
                        onFail = {
                            errorAvailable.value = ErrorMessages.SOMETHING_WENT_WRONG
                        }
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppBrown
                )
            ) {
                Text(
                    text = stringResource(id = R.string.continue_),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            BottomShadow(
                modifier = Modifier
                    .width(165.dp)
                    .height(8.dp)
                    .padding(horizontal = 0.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.1f),
                                Color.Transparent,
                            )
                        )
                    )
            )


            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.width(15.dp))
                Box(
                    modifier = Modifier
                        .padding(end = 250.dp)
                        .align(Alignment.Center)
                        .width(90.dp)
                        .height(1.dp)
                        .background(AppExtraLightBrown),
                )

                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 20.dp),
                    text = stringResource(id = R.string.other_sign_in_methods),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .padding(start = 250.dp)
                        .align(Alignment.Center)
                        .width(90.dp)
                        .height(1.dp)
                        .background(AppExtraLightBrown),
                )

                Spacer(modifier = Modifier.width(15.dp))
            }

            Spacer(modifier = Modifier.height(30.dp))
            // add facebook and google sign in buttons

            GoogleSignInButton(navController = navController)

            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.create_new_account),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ScreenId.RegistrationScreen.name)
                        },
                    text = stringResource(id = R.string.sign_up),
                    color = Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            if (errorAvailable.value.isNotEmpty())
                isAlertDialogOpenState.value = true

            CustomAlertDialogSample(
                message = ErrorMessages.SIGN_IN_FAILED,
                openDialog = isAlertDialogOpenState
            ) {
                errorAvailable.value = ""
            }
//                // create new architecture for handling error & success case
//                CustomSnackbar(
//                    returnMessage = ErrorMessages.SOMETHING_WENT_WRONG,
//                    backgroundColor = AppExtraLightBrown,
//                    textColor = AppDarkBlue
//                )
        }
    }
}

@Composable
fun GoogleSignInButton(
    navController: NavController,
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val clientId = context.getString(R.string.default_web_client_id)
    val gso = signInViewModel.generateGoogleSignInOptions(clientId)

    val googleSignInClient = signInViewModel.generateGoogleSignInClient(context, gso)
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                signInViewModel.googleSignInResultHandler(task) {
                    navController.navigate(ScreenId.MainScreenLine.name)
                }
            } else if (result.resultCode == RESULT_CANCELED) {
                println("Permission denied")
            }
        }


    Button(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 35.dp)
            .testTag("Registration with Google test tag"),
        onClick = {
            val signInClient = googleSignInClient.signInIntent
            launcher.launch(signInClient)
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(25.dp),
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google"
            )

            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.continue_with_google),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
