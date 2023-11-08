package com.natiqhaciyef.witapplication.presentation.screens.main.learn.interview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.EnumList
import com.natiqhaciyef.witapplication.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.data.models.top.QuestionAbstraction
import com.natiqhaciyef.witapplication.presentation.component.CustomDropDownMenu
import com.natiqhaciyef.witapplication.presentation.component.CustomDropDownTitleSelectionBox
import com.natiqhaciyef.witapplication.presentation.component.InputBox
import com.natiqhaciyef.witapplication.presentation.component.LevelComponent
import com.natiqhaciyef.witapplication.presentation.component.QuestionComponent
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.viewmodel.InterviewQuestionViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown
import okhttp3.internal.immutableListOf

@Composable
fun InterviewQuestions(
    navController: NavController,
    field: String,
    interviewQuestionViewModel: InterviewQuestionViewModel = hiltViewModel(),
) {
    val interviewQuestions = remember { interviewQuestionViewModel.interviewQuestionsUIState }
    val searchQuery = remember { mutableStateOf("") }
    val selectedLevel = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (interviewQuestions.value.list.any { it.field.lowercase() == field.lowercase() }) {

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

            Spacer(modifier = Modifier.height(20.dp))
            InputBox(
                concept = "",
                tag = stringResource(id = R.string.search),
                input = searchQuery,
                isSingleLine = true,
                leadingIcon = Icons.Default.Search,
                trailingIcon = Icons.Default.Tune,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    autoCorrect = true,
                    imeAction = ImeAction.Search
                ),
                isBottomShadowActive = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp),
            )

            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                items(EnumList.questionCategoriesLevels) { level ->
                    LevelComponent(level = level) {
                        selectedLevel.value = level
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn {
                items(
                    interviewQuestions.value.list.filter { it.field.lowercase() == field.lowercase() }
                ) { question ->
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
                    text = ErrorMessages.INTERVIEW_QUESTIONS_NOT_FOUND,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

