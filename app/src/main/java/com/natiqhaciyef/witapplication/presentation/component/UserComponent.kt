package com.natiqhaciyef.witapplication.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.witapplication.ui.theme.*


@Composable
fun TitleComponent(titleID: Int) {
    Box(
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth()
            .background(AppLightGray)
    ) {
        Text(
            text = stringResource(id = titleID),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterStart),
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun SubComponent(
    navigationId: () -> Unit = {},
    icon: ImageVector,
    iconSize: Dp,
    padding: Dp = 0.dp,
    textId: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clickable {
                navigationId()
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 25.dp)
                .height(50.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Component",
                modifier = Modifier
                    .padding(start = padding)
                    .size(iconSize),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = stringResource(id = textId),
                fontSize = 17.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun SubComponent(
    navigationId: () -> Unit = {},
    iconId: Int,
    padding: Dp = 0.dp,
    iconSize: Dp,
    textId: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clickable {
                navigationId()
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 25.dp)
                .height(50.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Component",
                modifier = Modifier
                    .padding(start = padding)
                    .size(iconSize),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = stringResource(id = textId),
                fontSize = 17.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}
