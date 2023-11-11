package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.common.Status
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.GetAllSavedInterviewQuestionsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.RemoveSavedInterviewQuestionUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.SaveInterviewQuestionUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.interview_question.GetAllInterviewQuestionsUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.interview_question.InsertInterviewQuestionUseCase
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewQuestionViewModel @Inject constructor(
    private val getAllInterviewQuestionsUseCase: GetAllInterviewQuestionsUseCase,
    private val insertInterviewQuestionUseCase: InsertInterviewQuestionUseCase,
    private val saveInterviewQuestionUseCase: SaveInterviewQuestionUseCase,
    private val removeSavedInterviewQuestionUseCase: RemoveSavedInterviewQuestionUseCase,
    private val getAllSavedInterviewQuestionsUseCase: GetAllSavedInterviewQuestionsUseCase,
) : BaseViewModel() {
    val interviewQuestionsUIState = mutableStateOf(UIState<InterviewQuestionModel>())
    val savedInterviewQuestionsUIState = mutableStateOf(UIState<InterviewQuestionModel>())

    init {
        getAllInterviewQuestions()
        getAllSavedQuestions()
//        insertInterviewQuestion(
//            questionModel = InterviewQuestionModel(
//                id = 0,
//                title = "Difference between const val and val ?",
//                description = "",
//                solution = "Val - mutable (partly-modifiable) data type which can modify while it refers to function. This data type is read-only and static until runtime.\n" +
//                        "Const - immutable data type which are not able to modify after run and only programmatically modifiable. It is static until compile time.",
//                image = null,
//                level = QuestionLevels.Easy.title,
//                origin = "https://www.geeksforgeeks.org/whats-the-difference-between-const-and-val-in-kotlin/",
//                field = "Mobile",
//                subfield = listOf("Kotlin", "Android").toSQLiteString(),
//                date = getNow()
//            )
//        )
    }

    // api request state holding
    private fun getAllInterviewQuestions() {
        viewModelScope.launch {
            getAllInterviewQuestionsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        interviewQuestionsUIState.value.isLoading = true
                    }

                    Status.ERROR -> {
                        interviewQuestionsUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            interviewQuestionsUIState.value.apply {
                                this.list = result.data!!
                                this.isLoading = false
                            }
                    }
                }
            }
        }
    }


    fun insertInterviewQuestion(questionModel: InterviewQuestionModel) {
        viewModelScope.launch {
            insertInterviewQuestionUseCase.invoke(questionModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        interviewQuestionsUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        interviewQuestionsUIState.value.isLoading = true
                    }

                    Status.ERROR -> {
                        interviewQuestionsUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }
                }
            }
        }
    }


    fun getAllSavedQuestions() {
        viewModelScope.launch {
            getAllSavedInterviewQuestionsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        savedInterviewQuestionsUIState.value =
                            savedInterviewQuestionsUIState.value.copy(isLoading = true)
                    }

                    Status.ERROR -> {
                        savedInterviewQuestionsUIState.value =
                            savedInterviewQuestionsUIState.value.copy(
                                message = result.message,
                                isLoading = false
                            )
                    }

                    Status.SUCCESS -> {
                        if (result.data != null) {
                            savedInterviewQuestionsUIState.value =
                                savedInterviewQuestionsUIState.value.copy(
                                    list = result.data!!,
                                    isLoading = false
                                )
                        }
                    }
                }
            }
        }
    }

    fun saveInterviewQuestion(questionModel: InterviewQuestionModel) {
        viewModelScope.launch {
            saveInterviewQuestionUseCase.invoke(questionModel).collectLatest { result ->
                when (result.status) {
                    Status.ERROR -> {
                        savedInterviewQuestionsUIState.value =
                            savedInterviewQuestionsUIState.value.copy(
                                message = result.message,
                                isLoading = false
                            )
                    }

                    Status.SUCCESS -> {
                        savedInterviewQuestionsUIState.value =
                            savedInterviewQuestionsUIState.value.copy(
                                isLoading = false,
                                message = result.data
                            )
                    }

                    Status.LOADING -> {
                        savedInterviewQuestionsUIState.value =
                            savedInterviewQuestionsUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun removeSavedInterviewQuestion(questionModel: InterviewQuestionModel) {
        viewModelScope.launch {
            removeSavedInterviewQuestionUseCase.invoke(questionModel).collectLatest { result ->
                when (result.status) {
                    Status.ERROR -> {
                        savedInterviewQuestionsUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.SUCCESS -> {
                        savedInterviewQuestionsUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        savedInterviewQuestionsUIState.value.isLoading = true
                    }
                }
            }
        }
    }
}