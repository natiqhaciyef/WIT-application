package com.natiqhaciyef.witapplication.presentation.screens.main.learn.exam

import android.os.Parcelable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.helpers.examTimer
import com.natiqhaciyef.util.models.QuestionModel
import com.natiqhaciyef.util.models.enums.QuestionCategories
import com.natiqhaciyef.util.models.top.QuestionAbstraction
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.component.QuestionComponent
import com.natiqhaciyef.witapplication.presentation.component.fonts.Lobster
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.screens.main.user.CustomAlertDialogSample
import com.natiqhaciyef.witapplication.presentation.viewmodel.ExamViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown
import com.natiqhaciyef.witapplication.ui.theme.DarkGreen
import com.natiqhaciyef.witapplication.ui.theme.DarkRed
import kotlinx.parcelize.Parcelize

@Composable
fun StartExamScreen(
    navController: NavController,
    field: String,
    examViewModel: ExamViewModel = hiltViewModel(),
) {
    val time = remember { examViewModel.timerState }
    val examState = remember { examViewModel.examUIState }
    if (examState.value.list.any { it.field.lowercase() == field.lowercase() }) {

        val exam = examState.value.list.filter { it.field.lowercase() == field.lowercase() }[0]
        val selectedOptionList = remember { mutableStateListOf<String>() }
        val isFinished = remember { mutableStateOf(false) }

        if (time.value == 1) {
            isFinished.value = true
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppExtraLightBrown)
                .padding(bottom = 48.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = examTimer(time.value),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = Opensans.opensans
            )
            Spacer(modifier = Modifier.height(20.dp))

            // add question lazy column
            for (question in exam.questions) {
                QuestionComponent(question = question) { selectedOption ->
                    selectedOptionList.add(selectedOption)
                }
            }
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier
                    .height(55.dp)
                    .width(220.dp),
                onClick = {
                    isFinished.value = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppDarkBlue
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.finish),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (isFinished.value) {
                val result = calculateScore(
                    questions = exam.questions,
                    selectedOptions = selectedOptionList
                )
                AlertDialog(
                    onDismissRequest = {
                        isFinished.value = false
                        navController.popBackStack()
                    },
                    title = {
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.exam_result),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (exam.limitPoint > result) DarkRed else DarkGreen
                        )

                    },
                    text = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier,
                                text = if (exam.limitPoint > result) "You failed" else "You passed",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = Opensans.opensans,
                                color = if (exam.limitPoint > result) DarkRed else DarkGreen
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontFamily = Opensans.opensans,
                                            color = AppDarkBlue
                                        )
                                    ) {
                                        append("Your result: ")
                                    }
                                    withStyle(
                                        SpanStyle(
                                            fontFamily = Lobster.lobster,
                                            color = if (exam.limitPoint > result) DarkRed else DarkGreen
                                        )
                                    ) {
                                        if (result - result.toInt() == 0.0)
                                            append("${result.toInt()} %")
                                        else
                                            append("$result %")
                                    }
                                },
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    },
                    confirmButton = {

                    },
                    shape = RoundedCornerShape(15.dp),
                    containerColor = AppExtraLightBrown
                )
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
                text = ErrorMessages.EMPTY_FIELD,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun calculateScore(questions: List<QuestionModel>, selectedOptions: List<String>): Double {
    return selectedOptions
        .mapNotNull { opt -> questions.find { q -> q.variants.contains(opt) && q.correctVariant == opt } }
        .sumByDouble { it.point }
}