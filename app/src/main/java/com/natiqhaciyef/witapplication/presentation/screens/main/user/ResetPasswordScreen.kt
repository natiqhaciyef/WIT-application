package com.natiqhaciyef.witapplication.presentation.screens.main.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.presentation.component.InputBox
import com.natiqhaciyef.witapplication.presentation.component.InputBoxPassword
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.viewmodel.UserViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppBrown
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val userState = remember { userViewModel.userUIState }
    userViewModel.getUser(userState.value.list)
    val email = remember { mutableStateOf("") }
    val oldPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(id = R.string.update_password),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 22.sp,
            fontFamily = Opensans.opensans
        )

        Spacer(modifier = Modifier.height(20.dp))
        InputBox(
            concept = "Email",
            input = email,
            isSingleLine = true,
            isBottomShadowActive = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            leadingIcon = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(10.dp))
        InputBoxPassword(
            concept = "Password",
            input = oldPassword,
            isSingleLine = true,
            isBottomShadowActive = false,
            passVisibility = passwordVisibility
        )

        Spacer(modifier = Modifier.height(10.dp))
        InputBoxPassword(
            concept = "New password",
            input = newPassword,
            isSingleLine = true,
            isBottomShadowActive = false,
            passVisibility = passwordVisibility
        )

        Spacer(modifier = Modifier.height(55.dp))

        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            onClick = {
                if (email.value == userState.value.selectedElement?.email
                    && oldPassword.value == userState.value.selectedElement?.password
                ) {
                    userViewModel.changePassword(
                        userList = userState.value.list,
                        email = email.value,
                        password = newPassword.value,
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppDarkBlue
            )
        ) {
            Text(
                text = stringResource(id = R.string.reset),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}