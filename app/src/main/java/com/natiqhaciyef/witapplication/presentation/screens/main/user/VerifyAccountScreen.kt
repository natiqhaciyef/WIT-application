package com.natiqhaciyef.witapplication.presentation.screens.main.user

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.component.ImageSelectionTitle
import com.natiqhaciyef.witapplication.presentation.component.InputBoxTitle
import com.natiqhaciyef.witapplication.presentation.component.InputBoxTitlePassword
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.viewmodel.UserViewModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.VerifiedUserViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Composable
fun VerifyAccountScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
    verifiedUserViewModel: VerifiedUserViewModel = hiltViewModel(),
) {
    val userState = remember { userViewModel.userUIState }
    userViewModel.getUser(userState.value.list)

    val phone = remember { mutableStateOf("") }
    val image = remember { mutableStateOf<Uri?>(null) }
    val idImage = remember { mutableStateOf<Uri?>(null) }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        if (userState.value.selectedElement != null) {
            InputBoxTitle(
                concept = "Full Name",
                input = remember { mutableStateOf(userState.value.selectedElement!!.name) },
                isEnabled = false,
                isSingleLine = true,
                isBottomShadowActive = false
            )
            Spacer(modifier = Modifier.height(10.dp))

            InputBoxTitle(
                concept = "Email",
                input = remember { mutableStateOf(userState.value.selectedElement?.email ?: "") },
                isEnabled = false,
                isSingleLine = true,
                isBottomShadowActive = false
            )
            Spacer(modifier = Modifier.height(10.dp))

        } else {
            InputBoxTitle(
                concept = "Full Name",
                input = remember { mutableStateOf(userState.value.selectedElement?.name ?: "") },
                isEnabled = false,
                isSingleLine = true,
                isBottomShadowActive = false
            )
            Spacer(modifier = Modifier.height(10.dp))

            InputBoxTitle(
                concept = "Email",
                input = remember { mutableStateOf(userState.value.selectedElement?.email ?: "") },
                isEnabled = false,
                isSingleLine = true,
                isBottomShadowActive = false
            )
            Spacer(modifier = Modifier.height(10.dp))

        }

        InputBoxTitle(
            concept = "Phone",
            input = phone,
            prefix = "+994 ",
            isEnabled = true,
            isSingleLine = true,
            isBottomShadowActive = false
        )

        Spacer(modifier = Modifier.height(10.dp))
        InputBoxTitlePassword(
            concept = "Password",
            input = password,
            isSingleLine = true,
            isBottomShadowActive = false,
            passVisibility = passwordVisible,
        )

        Spacer(modifier = Modifier.height(10.dp))
        ImageSelectionTitle(image = image, concept = "Personal image")

        Spacer(modifier = Modifier.height(10.dp))
        ImageSelectionTitle(image = idImage, concept = "ID image")

        Spacer(modifier = Modifier.height(55.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 40.dp),
            onClick = {
                if (password.value == userState.value.selectedElement?.password) {
                    val verifiedUserModel = VerifiedUserModel(
                        id = 0,
                        name = userState.value.selectedElement?.name ?: "",
                        email = userState.value.selectedElement?.email ?: "",
                        password = password.value,
                        phone = phone.value,
                        image = image.value.toString(),
                        idImage = idImage.value.toString(),
                    )

                    verifiedUserViewModel.insertVerifiedUser(verifiedUserModel) {
                        navController.popBackStack()
                    }
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppDarkBlue
            )
        ) {
            Text(
                text = stringResource(id = R.string.apply),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}