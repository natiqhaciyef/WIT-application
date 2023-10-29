package com.natiqhaciyef.witapplication.presentation.screens.main.learn.interview

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.data.models.top.QuestionAbstraction
import com.natiqhaciyef.witapplication.presentation.component.QuestionComponent
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.viewmodel.InterviewQuestionViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@Composable
fun InterviewQuestions(
    navController: NavController,
    field: String,
    interviewQuestionViewModel: InterviewQuestionViewModel = hiltViewModel(),
) {
    val interviewQuestions = remember { interviewQuestionViewModel.interviewQuestionsUIState }
    val filteredList =
        interviewQuestionViewModel.filterQuestionsByField(field, interviewQuestions.value.list)

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
            text = stringResource(id = R.string.interview_questions),
            fontFamily = Opensans.opensans,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp
        )

        if (filteredList.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn {
                items(filteredList) { question ->
                    QuestionComponent(question = question)
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
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
                    text = ErrorMessages.APPLICATION_UNDER_THE_TEST,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

