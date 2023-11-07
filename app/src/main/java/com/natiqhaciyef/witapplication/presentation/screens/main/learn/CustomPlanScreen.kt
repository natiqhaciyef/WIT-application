package com.natiqhaciyef.witapplication.presentation.screens.main.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.SuccessMessages
import com.natiqhaciyef.witapplication.data.models.ContactModel
import com.natiqhaciyef.witapplication.presentation.component.InputBox
import com.natiqhaciyef.witapplication.presentation.component.CustomSnackbar
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.viewmodel.ContactViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Preview
@Composable
fun CustomPlanScreen(
    navController: NavController = rememberNavController(),
    contactViewModel: ContactViewModel = hiltViewModel(),
    field: String = ""
) {
    val fullName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(id = R.string.contact_with_us),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = Opensans.opensans
        )

        Spacer(modifier = Modifier.height(25.dp))
        InputBox(
            concept = stringResource(id = R.string.full_name),
            input = fullName,
            isSingleLine = true,
            isBottomShadowActive = false,
            leadingIcon = Icons.Default.AccountCircle
        )

        Spacer(modifier = Modifier.height(15.dp))
        InputBox(
            concept = stringResource(id = R.string.email),
            input = email,
            isSingleLine = true,
            isBottomShadowActive = false,
            leadingIcon = Icons.Default.Email
        )

        Spacer(modifier = Modifier.height(15.dp))
        InputBox(
            concept = stringResource(id = R.string.phone),
            input = phone,
            isSingleLine = true,
            isBottomShadowActive = false,
            leadingIcon = Icons.Default.Phone
        )

        Spacer(modifier = Modifier.height(15.dp))
        InputBox(
            concept = stringResource(id = R.string.description),
            input = description,
            isSingleLine = false,
            isBottomShadowActive = false,
            leadingIcon = Icons.Default.Description
        )

        Spacer(modifier = Modifier.height(45.dp))
        Button(
            modifier = Modifier
                .height(55.dp)
                .width(220.dp),
            onClick = {
                val contact = ContactModel(
                    name = fullName.value,
                    email = email.value,
                    phone = phone.value,
                    description = description.value,
                    field = field
                )

                contactViewModel.insertContactRemote(contact) {
                    contactViewModel.saveContactLocal(contact) {
                        CustomSnackbar(returnMessage = SuccessMessages.MESSAGE_SENT_SUCCESSFULLY)
                    }
                }

            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppDarkBlue
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.submit),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}