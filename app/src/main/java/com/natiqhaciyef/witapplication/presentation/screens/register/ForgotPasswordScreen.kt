package com.natiqhaciyef.witapplication.presentation.screens.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.natiqhaciyef.witapplication.presentation.component.BottomShadow
import com.natiqhaciyef.witapplication.presentation.component.CustomSnackbar
import com.natiqhaciyef.witapplication.presentation.component.InputBox
import com.natiqhaciyef.witapplication.presentation.component.fonts.Lobster
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.viewmodel.FirebaseViewModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.UserViewModel
import com.natiqhaciyef.witapplication.ui.theme.*

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ellipse_design_5),
            contentDescription = "Ellipse 1",
            modifier = Modifier
                .fillMaxWidth(0.37f)
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
            ForgotPasswordTopView()
            ForgotPasswordMainPart(navController = navController)
        }
    }
}


@Composable
private fun ForgotPasswordTopView() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.reset_password_animation),
    )
    Spacer(modifier = Modifier.height(70.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }

    Spacer(modifier = Modifier.height(30.dp))
}


@Composable
private fun ForgotPasswordMainPart(
    navController: NavController,
    firebaseViewModel: FirebaseViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val errorAvailable = remember { mutableStateOf("") }
    val userState = remember { userViewModel.userUIState }
    val email = remember { mutableStateOf("") }

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
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(id = R.string.reset_password),
                fontSize = 25.sp,
                color = White,
                fontFamily = Lobster.lobster,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(15.dp))


            InputBox(
                concept = stringResource(id = R.string.email),
                input = email,
                isSingleLine = true,
                leadingIcon = Icons.Default.Email
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                text = stringResource(id = R.string.check_message),
                color = White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(60.dp))

            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                onClick = {
                    firebaseViewModel.resetPasswordFromEmail(
                        email = email.value,
                        onSuccess = {
                            userViewModel.clearPassword(
                                userState = userState,
                                email = email
                            )
                            errorAvailable.value = ""
                            navController.navigate(ScreenId.LoginScreen.name)
                        }, onFail = {
                            errorAvailable.value = ErrorMessages.SOMETHING_WENT_WRONG
                        })
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AppBrown
                )
            ) {
                Text(
                    text = stringResource(id = R.string.send),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
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
            if (errorAvailable.value.isNotEmpty()) {
                CustomSnackbar(
                    returnMessage = errorAvailable.value,
                    backgroundColor = AppExtraLightBrown,
                    textColor = AppDarkBlue
                )
            }
        }
    }
}

