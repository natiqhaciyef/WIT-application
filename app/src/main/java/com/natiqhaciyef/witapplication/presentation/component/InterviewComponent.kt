package com.natiqhaciyef.witapplication.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.natiqhaciyef.util.common.util.helpers.majorStringToDateChanger
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.util.models.top.QuestionAbstraction
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppDarkGray
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Composable
fun <T : QuestionAbstraction> QuestionComponent(question: T) {
    var isVisible by remember { mutableStateOf(false) }

    when (question) {
        is InterviewQuestionModel -> {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp)
                    .animateContentSize(tween(600, 100))
                    .clickable {
                        isVisible = !isVisible
                    },
                shape = RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        text = question.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppDarkBlue
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AnimatedVisibility(
                        visible = isVisible,
                        enter = fadeIn(animationSpec = tween(600, 100)),
                        exit = shrinkVertically(animationSpec = tween(600, 100))
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (question.description.isNotEmpty())
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            SpanStyle(
                                                color = Color.Black
                                            )
                                        ) {
                                            append("Description: ")
                                        }
                                        withStyle(
                                            SpanStyle(
                                                color = AppDarkGray
                                            )
                                        ) {
                                            append(question.description)
                                        }
                                    },
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp, vertical = 10.dp)
                                )

                            if (!question.image.isNullOrEmpty()) {
                                Image(
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .size(220.dp),
                                    painter = rememberImagePainter(question.image),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(color = Color.Black)
                                    ) {
                                        append("Solution: ")
                                    }

                                    withStyle(
                                        SpanStyle(color = AppDarkGray)
                                    ) {
                                        append(question.solution)
                                    }
                                },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Field: ${question.field} (${question.level})",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp)
                            )
                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                modifier = Modifier
                                    .padding(bottom = 20.dp, start = 10.dp, end = 10.dp),
                                text = "Published: ${majorStringToDateChanger(question.date)}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }

//         -> {
//            question as InterviewQuestionModel
//
//        }
        else -> {}

    }
}

@Composable
fun LevelComponent(level: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .width(130.dp)
            .height(45.dp)
            .background(AppDarkBlue)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            text = level,
            color = AppExtraLightBrown,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}