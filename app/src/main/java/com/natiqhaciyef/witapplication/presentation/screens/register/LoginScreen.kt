package com.natiqhaciyef.witapplication.presentation.screens.register

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.natiqhaciyef.witapplication.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.presentation.component.BottomShadow
import com.natiqhaciyef.witapplication.presentation.component.InputBox
import com.natiqhaciyef.witapplication.presentation.component.InputBoxPassword
import com.natiqhaciyef.witapplication.presentation.component.fonts.Lobster
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.viewmodel.FirebaseViewModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.UserViewModel
import com.natiqhaciyef.witapplication.ui.theme.*


@Composable
fun LoginScreen(
    navController: NavController
) {

    Box(modifier = Modifier.fillMaxSize()) {
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
                .height(260.dp),
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
    firebaseViewModel: FirebaseViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userState = remember { userViewModel.userUIState }
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
                .fillMaxSize(),
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
            Spacer(modifier = Modifier.height(60.dp))

            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                onClick = {
                    val email = emailState.value
                    val password = passwordState.value

                    val user = UserModel(name = " ", email = email, password = password)
                    firebaseViewModel.signInUser(user,
                        onFail = {
                            Toast.makeText(
                                context,
                                ErrorMessages.SOMETHING_WENT_WRONG,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        },
                        onSuccess = {
                            userViewModel.userLoginCheckFromDB(
                                userState = userState,
                                email = email,
                                password = password,
                                onSuccess = {
                                    navController.navigate(ScreenId.MainScreenLine.name){
                                        navController.popBackStack(ScreenId.LoginScreen.name, inclusive = true)
                                    }
                                },
                                onFail = {
                                    Toast.makeText(
                                        context,
                                        ErrorMessages.SIGN_IN_FAILED,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            )
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

            Spacer(modifier = Modifier.height(10.dp))
            Row(
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
        }
    }
}

