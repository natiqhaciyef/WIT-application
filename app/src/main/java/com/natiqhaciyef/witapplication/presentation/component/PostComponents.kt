package com.natiqhaciyef.voyagersaz.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.natiqhaciyef.voyagersaz.domain.model.MappedPostModel
import com.natiqhaciyef.voyagersaz.ui.theme.AppDarkBlue
import com.natiqhaciyef.voyagersaz.ui.theme.AppDarkGray

@Composable
fun PostComponent(
    mappedPostModel: MappedPostModel
) {
    var isVisible by remember { mutableStateOf(false) }

    val modifier = if (isVisible) Modifier else Modifier.height(112.dp)

    Card(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clickable {
                isVisible = !isVisible
            },
        shape = RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            if (!isVisible)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    text = mappedPostModel.title,
                    maxLines = 1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppDarkBlue
                )
            else
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    text = mappedPostModel.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppDarkBlue
                )

            Spacer(modifier = Modifier.height(17.dp))
            if (!isVisible)
                Text(
                    text = mappedPostModel.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppDarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                )

            AnimatedVisibility(visible = isVisible) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = mappedPostModel.description,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppDarkGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
                    )

                    if (mappedPostModel.image.isNotEmpty()) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            painter = rememberImagePainter(mappedPostModel.image),
                            contentDescription = "Custom Image"
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp,bottom = 10.dp, start = 10.dp),
                        text = "Published: ${mappedPostModel.publishDate}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }


        }
    }
}