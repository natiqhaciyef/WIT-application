package com.natiqhaciyef.witapplication.presentation.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.data.models.service.NotificationModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.FirebaseViewModel
import com.natiqhaciyef.witapplication.ui.theme.*

@Preview
@Composable
fun NotificationScreen(
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {
    val notificationState = remember { firebaseViewModel.notificationState }

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
            text = stringResource(id = R.string.notifications),
            fontWeight = FontWeight.Bold,
            color = AppDarkBlue,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (notificationState.value.list.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(notificationState.value.list) { notification ->
                    NotificationComponent(notification = notification)
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                androidx.compose.material.Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 90.dp)
                        .align(Alignment.Center),
                    text = ErrorMessages.NOTIFICATION_NOT_SENT,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun NotificationComponent(
    notification: NotificationModel
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 3.dp)
            .fillMaxWidth()
            .height(88.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.wit_icon),
                contentDescription = "Notification Type",
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = notification.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = notification.description,
                    fontWeight = FontWeight.SemiBold,
                    color = AppDarkGray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }
}