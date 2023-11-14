package com.natiqhaciyef.witapplication.presentation.screens.main.learn.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.component.ExamComponent
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.screens.main.user.CustomAlertDialogSample
import com.natiqhaciyef.witapplication.presentation.viewmodel.ExamViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Composable
fun ExamScreen(
    navController: NavController,
    examViewModel: ExamViewModel = hiltViewModel(),
) {
    val examState = remember { examViewModel.examUIState }

    if (examState.value.list.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppExtraLightBrown)
                .padding(bottom = 65.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(id = R.string.exams),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = Opensans.opensans
            )
            Spacer(modifier = Modifier.height(20.dp))

            // add question lazy column
            LazyColumn {
                items(examState.value.list) { exam ->
                    ExamComponent(exam = exam) { isApplied ->
                        if (!examViewModel.participatedExamUIState.value.list.contains(exam)) {
                            examViewModel.participateExam(exam)
                            CustomAlertDialogSample(
                                openDialog = isApplied,
                                messageId = R.string.exam_alert
                            ) {
                                navController.navigate("${ScreenId.StartExamScreen.name}/${exam.field}")
                            }
                        } else {
                            CustomExamAlertDialog(isApplied = isApplied)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    } else if (examState.value.isLoading && examState.value.message == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppExtraLightBrown)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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
                    .padding(bottom = 80.dp)
                    .align(Alignment.Center),
                text = ErrorMessages.EXAMS_NOT_FOUND,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CustomExamAlertDialog(isApplied: MutableState<Boolean>) {
    Column {
        if (isApplied.value) {
            AlertDialog(
                onDismissRequest = {
                    isApplied.value = false
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.alert),
                        fontSize = 18.sp,
                        color = AppDarkBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.exam_taken_alert),
                        fontSize = 18.sp,
                        color = AppDarkBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                },
                confirmButton = {},
                dismissButton = {
                    Button(
                        modifier = Modifier
                            .width(120.dp)
                            .height(55.dp),
                        onClick = {
                            isApplied.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppDarkBlue
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent)
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = stringResource(id = R.string.alert_dialog_positive_button),
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(15.dp),
                containerColor = AppExtraLightBrown
            )
        }
    }

}