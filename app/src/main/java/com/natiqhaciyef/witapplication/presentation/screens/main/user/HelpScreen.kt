package com.natiqhaciyef.witapplication.presentation.screens.main.user

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.EnumList
import com.natiqhaciyef.witapplication.data.models.enums.ContactFields
import com.natiqhaciyef.witapplication.data.models.service.InfoModel
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.viewmodel.FirebaseViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppDarkGray
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Composable
fun HelpScreen(navController: NavController) {
    val context = LocalContext.current
    val helpFAQ = EnumList.getAllHelpInformation(context)

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
            text = stringResource(id = R.string.how_help),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 22.sp,
            fontFamily = Opensans.opensans
        )

        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn {
            items(helpFAQ) { help ->
                HelpInfoBox(helpModel = help) {
                    navController.navigate("${ScreenId.CustomPlanScreen.name}/${ContactFields.HELP_SUPPORT.name.lowercase()}")
                }
            }
        }
        Spacer(modifier = Modifier.height(65.dp))

    }
}

@Composable
fun HelpInfoBox(
    helpModel: InfoModel,
    onClickAction: () -> Unit = {},
) {
    var isClicked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .animateContentSize(animationSpec = tween(600, 100))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .clickable {
                isClicked = !isClicked
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 20.dp),
                    text = helpModel.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 20.dp)
                        .size(30.dp),
                    imageVector = if (isClicked) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                    contentDescription = "Arrow",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(visible = isClicked,
                enter = expandVertically(animationSpec = tween(50, 50))
            ) {
                if (helpModel.title.lowercase() == "other") {
                    Button(
                        modifier = Modifier
                            .padding(bottom = 15.dp)
                            .height(55.dp)
                            .width(220.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppDarkBlue
                        ),
                        onClick = {
                            onClickAction()
                        },
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = stringResource(id = R.string.submit),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                    }
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                            .padding(horizontal = 20.dp),
                        text = helpModel.description,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppDarkGray
                    )
                }
            }
        }
    }

}