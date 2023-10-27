package com.natiqhaciyef.witapplication.presentation.screens.main.user

import android.content.Context
import android.net.ConnectivityManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.work.impl.utils.getActiveNetworkCompat
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.data.models.ContactModel
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.viewmodel.ContactViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Suppress("DEPRECATION")
@Composable
fun SavedContactScreen(
    navController: NavController,
    contactViewModel: ContactViewModel = hiltViewModel()
) {
    val contactLocal = remember { contactViewModel.savedContactUIState }
    val contactRemote = remember { contactViewModel.contactUIState }
    val context = LocalContext.current

    val connectivityManager =
        remember { getSystemService(context, ConnectivityManager::class.java) }
    val isNetworkAvailable = remember { mutableStateOf(false) }

//    val activeNetworkInfo = connectivityManager?.getActiveNetworkCompat()
    val networkInfo = connectivityManager?.activeNetworkInfo
    isNetworkAvailable.value = networkInfo?.isConnected == true


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
            text = stringResource(id = R.string.last_contact_requests),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            fontFamily = Opensans.opensans,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(
                if (isNetworkAvailable.value) contactRemote.value.list else contactLocal.value.list
            ) { contact ->
                ContactCardComponent(contactModel = contact)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun ContactCardComponent(contactModel: ContactModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.77f)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = contactModel.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Date: ${contactModel.date}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                text = if (contactModel.isChecked) "Status: checked" else "Status: Unchecked",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
        }
    }
}