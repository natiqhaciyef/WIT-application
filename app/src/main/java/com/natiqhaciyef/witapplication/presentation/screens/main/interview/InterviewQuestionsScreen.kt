package com.natiqhaciyef.witapplication.presentation.screens.main.interview

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.EnumList
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.component.InputBox
import com.natiqhaciyef.witapplication.presentation.component.LevelComponent
import com.natiqhaciyef.witapplication.presentation.component.QuestionComponent
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InterviewQuestions(
    navController: NavController,
    field: String,
    interviewQuestionViewModel: InterviewQuestionViewModel = hiltViewModel(),
) {
    val interviewQuestions = remember { interviewQuestionViewModel.interviewQuestionsBaseUIState }
    val savedQuestions = remember { interviewQuestionViewModel.savedInterviewQuestionsBaseUIState }
    val searchQuery = remember { mutableStateOf("") }
    val selectedLevel = remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .padding(bottom = 55.dp),
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
                        if (level == "All")
                            selectedLevel.value = ""
                        else
                            selectedLevel.value = level
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn {
                items(
                    interviewQuestions.value.list.filter {
                        it.field.lowercase() == field.lowercase()
                                && it.title.lowercase().contains(searchQuery.value.lowercase())
                                && it.level.contains(selectedLevel.value)
                    }
                ) { question ->

                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart) {
                                if (question.image == null)
                                    question.image = "Empty image"

                                if (!savedQuestions.value.list
                                        .map { Pair(it.title, it.solution) }
                                        .contains(Pair(question.title, question.solution))
                                )
                                    interviewQuestionViewModel.saveInterviewQuestion(question)
                            }

                            interviewQuestionViewModel.getAllSavedQuestions()


                            true
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            val backgroundColor by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.DismissedToStart -> AppDarkBlue.copy(alpha = 0.8f)
                                    DismissValue.DismissedToEnd -> Color.Transparent.copy(alpha = 0.8f)
                                    else -> Color.Transparent
                                },
                                label = ""
                            )

                            // icon size
                            val iconScale by animateFloatAsState(
                                targetValue = if (dismissState.targetValue == DismissValue.DismissedToStart) 1.3f else 0.5f,
                                label = ""
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color = backgroundColor)
                                    .padding(end = 16.dp)
                                    .clickable {
                                        coroutineScope.launch {
                                            interviewQuestionViewModel.removeSavedInterviewQuestion(
                                                question
                                            )

                                            dismissState.reset()
                                        }
                                    }, // inner padding
                                contentAlignment = Alignment.CenterEnd // place the icon at the end (left)
                            ) {
                                Icon(
                                    modifier = Modifier.scale(iconScale),
                                    imageVector = Icons.Outlined.BookmarkBorder,
                                    contentDescription = "Save",
                                    tint = Color.White
                                )
                            }
                        },
                        directions = setOf(DismissDirection.EndToStart)
                    ) {

                        QuestionComponent(question = question)
                    }
                }
            }
        } else if (interviewQuestions.value.isLoading) {
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

