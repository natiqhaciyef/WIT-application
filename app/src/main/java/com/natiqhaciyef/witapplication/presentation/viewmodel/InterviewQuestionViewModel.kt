package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.common.Status
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.GetAllSavedInterviewQuestionsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.RemoveSavedInterviewQuestionUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.SaveInterviewQuestionUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.interview_question.GetAllInterviewQuestionsUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.interview_question.InsertInterviewQuestionUseCase
import com.natiqhaciyef.util.common.util.helpers.getNow
import com.natiqhaciyef.util.common.util.helpers.toSQLiteString
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.util.models.enums.QuestionCategories
import com.natiqhaciyef.util.models.enums.QuestionLevels
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
//                title = "What is the difference between JDK, JRE, and JVM ?",
//                description = "",
//                solution = "JVM\n" +
//                        "JVM is an acronym for Java Virtual Machine; it is an abstract machine which provides the runtime environment in which Java bytecode can be executed. It is a specification which specifies the working of Java Virtual Machine. Its implementation has been provided by Oracle and other companies. Its implementation is known as JRE.\n" +
//                        "\n" +
//                        "JVMs are available for many hardware and software platforms (so JVM is platform dependent). It is a runtime instance which is created when we run the Java class. There are three notions of the JVM: specification, implementation, and instance.\n" +
//                        "\n" +
//                        "JRE\n" +
//                        "JRE stands for Java Runtime Environment. It is the implementation of JVM. The Java Runtime Environment is a set of software tools which are used for developing Java applications. It is used to provide the runtime environment. It is the implementation of JVM. It physically exists. It contains a set of libraries + other files that JVM uses at runtime.\n" +
//                        "\n" +
//                        "JDK\n" +
//                        "JDK is an acronym for Java Development Kit. It is a software development environment which is used to develop Java applications and applets. It physically exists. It contains JRE + development tools. JDK is an implementation of any one of the below given Java Platforms released by Oracle Corporation:\n" +
//                        "Standard Edition Java Platform\n" +
//                        "Enterprise Edition Java Platform\n" +
//                        "Micro Edition Java Platform",
//                image = null,
//                level = QuestionLevels.MEDIUM.title,
//                origin = "https://www.javatpoint.com/corejava-interview-questions",
//                field = QuestionCategories.BACKEND.title,
//                subfield = listOf(QuestionCategories.JAVA.title, QuestionCategories.BACKEND.title).toSQLiteString(),
//                date = getNow() ?: "20.11.2023 09:15"
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