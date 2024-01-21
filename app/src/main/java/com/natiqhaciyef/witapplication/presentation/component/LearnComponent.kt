package com.natiqhaciyef.witapplication.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.models.service.InfoLangModel
import com.natiqhaciyef.util.models.service.InfoModel
import com.natiqhaciyef.util.models.service.LanguageModel
import com.natiqhaciyef.witapplication.presentation.screens.main.language.loadLocale
import com.natiqhaciyef.witapplication.ui.theme.AppDarkGray


@Composable
fun FAQInfoBox(
    infoModel: InfoLangModel,
    langModel: LanguageModel = LanguageModel("az", "Azerbaijani", isSelected = false)
) {
    val isClicked = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .clickable {
                isClicked.value = !isClicked.value
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            FAQLanguageTitle(langModel = langModel, infoModel = infoModel, isClicked = isClicked)

            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(
                visible = isClicked.value,
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = tween(800, 0),
                    )
            ) {
                FAQLanguageDescription(langModel = langModel, infoModel = infoModel)
            }
        }
    }

}

@Composable
fun FAQLanguageTitle(
    langModel: LanguageModel,
    infoModel: InfoLangModel,
    isClicked: MutableState<Boolean>
) = when (langModel.title) {
    "az" -> {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 20.dp),
                text = infoModel.titleAz,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp)
                    .size(30.dp),
                imageVector = if (isClicked.value) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                contentDescription = "Arrow",
                tint = Color.Black
            )
        }
    }

    "en" -> {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 20.dp),
                text = infoModel.titleEng,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp)
                    .size(30.dp),
                imageVector = if (isClicked.value) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                contentDescription = "Arrow",
                tint = Color.Black
            )
        }
    }

    "tr" -> {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 20.dp),
                text = infoModel.titleTr,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 20.dp)
                    .size(30.dp),
                imageVector = if (isClicked.value) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                contentDescription = "Arrow",
                tint = Color.Black
            )
        }
    }

    else -> {
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
                imageVector = if (isClicked.value) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropUp,
                contentDescription = "Arrow",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun FAQLanguageDescription(
    langModel: LanguageModel,
    infoModel: InfoLangModel,
) = when (langModel.title) {
    "az" -> {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
                .padding(horizontal = 20.dp),
            text = infoModel.descriptionAz,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppDarkGray
        )
    }

    "en" -> {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
                .padding(horizontal = 20.dp),
            text = infoModel.descriptionEng,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppDarkGray
        )
    }

    "tr" -> {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
                .padding(horizontal = 20.dp),
            text = infoModel.descriptionTr,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppDarkGray
        )
    }

    else -> {
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