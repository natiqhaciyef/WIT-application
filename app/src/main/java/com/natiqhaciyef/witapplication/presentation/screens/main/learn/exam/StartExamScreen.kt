package com.natiqhaciyef.witapplication.presentation.screens.main.learn.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.helpers.examTimer
import com.natiqhaciyef.witapplication.presentation.component.QuestionComponent
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.viewmodel.ExamViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Composable
fun StartExamScreen(
    navController: NavController,
    field: String,
    examViewModel: ExamViewModel = hiltViewModel(),
) {
    val time = remember { examViewModel.timerState }
    val examState = remember { examViewModel.examState }
    if (examState.value.list
            .filter { it.field.lowercase() == field.lowercase() }
            .isNotEmpty()
    ) {

        val exam = examState.value.list.filter { it.field.lowercase() == field.lowercase() }[0]
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
                text = examTimer(time.value),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontFamily = Opensans.opensans
            )
            Spacer(modifier = Modifier.height(20.dp))

            // add question lazy column
            LazyColumn {
                items(exam.questions) { question ->
                    QuestionComponent(question = question)
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
                text = ErrorMessages.EMPTY_FIELD,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}