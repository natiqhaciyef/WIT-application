package com.natiqhaciyef.witapplication.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.screens.main.user.CustomAlertDialogSample
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppDarkGray

@Composable
fun ExamComponent(
    exam: MappedExamModel,
    onClick: @Composable (MutableState<Boolean>) -> Unit = {},
) {
    var isVisible by remember { mutableStateOf(false) }
    val isApplied = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .animateContentSize(tween(600, 100)),
        shape = RoundedCornerShape(topStart = 20.dp, bottomEnd = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .clickable {
                    isVisible = !isVisible
                }
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                text = exam.title,
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
                    if (exam.image.isNotEmpty()) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(40.dp)
                                .aspectRatio(1f),
                            painter = rememberImagePainter(exam.image),
                            contentDescription = "Image"
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = Color.Black
                                )
                            ) {
                                append("Field: ")
                            }
                            withStyle(
                                SpanStyle(
                                    color = AppDarkGray
                                )
                            ) {
                                append(exam.field)
                            }
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(color = Color.Black)
                            ) {
                                append("Level: ")
                            }

                            withStyle(
                                SpanStyle(color = AppDarkGray)
                            ) {
                                append(exam.level)
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
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(color = Color.Black)
                            ) {
                                append("Limit pass point: ")
                            }

                            withStyle(
                                SpanStyle(color = AppDarkGray)
                            ) {
                                append("${exam.limitPoint}")
                            }
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        text = if (!exam.isActive) "Exam is not active" else "Exam is active",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 15.dp)
                            .height(50.dp),
                        onClick = {
                            isApplied.value = !isApplied.value
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppDarkBlue
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = stringResource(id = R.string.apply),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    if (isApplied.value)
                        onClick(isApplied)
                }
            }
        }
    }
}