package com.natiqhaciyef.witapplication.presentation.screens.home.learn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.DefaultImpl
import com.natiqhaciyef.witapplication.data.models.InfoModel
import com.natiqhaciyef.witapplication.data.models.LearnSectionModel
import com.natiqhaciyef.witapplication.presentation.navigation.NavStandards
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.viewmodel.FirebaseViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppDarkGray
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Preview
@Composable
fun LearnScreen(
    navController: NavController = rememberNavController(),
    firebaseViewModel: FirebaseViewModel = hiltViewModel()
) {
    val faqState = remember { firebaseViewModel.faqState }

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
            text = stringResource(id = R.string.learn_from),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            LearnSection(
                lsm = LearnSectionModel(title = "Interview practice", icon = Icons.Default.MenuBook)
            ) {
                navController.navigate("${ScreenId.FieldScreen.name}/${NavStandards.INTERVIEW}")
            }

            Spacer(modifier = Modifier.width(15.dp))
            LearnSection(
                lsm = LearnSectionModel(title = "Live interview", icon = Icons.Default.LiveTv)
            ) {
                navController.navigate(ScreenId.LiveInterviewScreen.name)
            }
            Spacer(modifier = Modifier.width(20.dp))
        }

        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            LearnSection(
                lsm = LearnSectionModel(title = "Materials", icon = Icons.Default.Article)
            ) {
                navController.navigate("${ScreenId.FieldScreen.name}/${NavStandards.MATERIAL}")
            }

            Spacer(modifier = Modifier.width(15.dp))
            LearnSection(
                lsm = LearnSectionModel(title = "Custom plan", icon = Icons.Default.ContactSupport)
            ) {
                navController.navigate(ScreenId.CustomPlanScreen.name)
            }
            Spacer(modifier = Modifier.width(20.dp))
        }

        Spacer(modifier = Modifier.height(35.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(id = R.string.faq),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (faqState.value.list.isNotEmpty()) {
            LazyColumn {
                items(faqState.value.list) { faqModel ->
                    FAQInfoBox(infoModel = faqModel)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppExtraLightBrown)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 30.dp)
                        .align(Alignment.Center),
                    text = stringResource(id = R.string.app_under_test),
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
private fun LearnSection(
    lsm: LearnSectionModel = DefaultImpl.lsm,
    navResult: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .size(165.dp)
            .clickable {

            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
        ) {
            Icon(
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .size(70.dp)
                    .align(Alignment.Center),
                imageVector = lsm.icon ?: Icons.Default.HourglassEmpty,
                contentDescription = "Learn section icon",
                tint = AppDarkBlue
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 15.dp),
                text = lsm.title,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FAQInfoBox(infoModel: InfoModel) {
    var isClicked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .animateContentSize(animationSpec = tween(600, 100))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
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
                    text = infoModel.title,
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
            AnimatedVisibility(visible = isClicked) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                        .padding(horizontal = 20.dp),
                    text = infoModel.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppDarkGray
                )
            }
        }
    }

}