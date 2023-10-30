package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.witapplication.common.Status
import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.domain.usecase.remote.interview_question.GetAllInterviewQuestionsUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.interview_question.InsertInterviewQuestionUseCase
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewQuestionViewModel @Inject constructor(
    private val getAllInterviewQuestionsUseCase: GetAllInterviewQuestionsUseCase,
    private val insertInterviewQuestionUseCase: InsertInterviewQuestionUseCase,
) : BaseViewModel() {
    val interviewQuestionsUIState = mutableStateOf(UIState<InterviewQuestionModel>())

    init {
        getAllInterviewQuestions()
    }

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
                                this.list = result.data
                                this.isLoading = false
                            }
                    }
                }
            }
        }
    }

    fun filterQuestionsByField(field: String, list: List<InterviewQuestionModel>) =
        list.filter { it.field.lowercase() == field.lowercase() }

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
}