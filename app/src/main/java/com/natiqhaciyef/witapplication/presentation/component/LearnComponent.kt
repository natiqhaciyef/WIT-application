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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.util.models.service.InfoModel
import com.natiqhaciyef.witapplication.ui.theme.AppDarkGray


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
