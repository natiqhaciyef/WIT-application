package com.natiqhaciyef.witapplication.presentation.screens.main.interview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.util.common.Status
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.GetAllSavedInterviewQuestionsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.RemoveSavedInterviewQuestionUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.SaveInterviewQuestionUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.interview_question.GetAllInterviewQuestionsUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.interview_question.InsertInterviewQuestionUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
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
    val interviewQuestionsBaseUIState = mutableStateOf(BaseUIState<InterviewQuestionModel>())
    val savedInterviewQuestionsBaseUIState = mutableStateOf(BaseUIState<InterviewQuestionModel>())

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
                        interviewQuestionsBaseUIState.value =
                            interviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = true,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = false,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        interviewQuestionsBaseUIState.value =
                            interviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = true,
                                isFailMessage = ErrorMessages.ERROR,
                                isFailReason = Exception(result.message),
                            )

                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            interviewQuestionsBaseUIState.value =
                                interviewQuestionsBaseUIState.value.copy(
                                    data = null,
                                    list = result.data!!,
                                    isLoading = false,
                                    isSuccess = true,
                                    isSuccessMessage = null,
                                    isFail = false,
                                    isFailMessage = null,
                                    isFailReason = null,
                                )
                    }

                }
            }
        }
    }


    fun insertInterviewQuestion(questionModel: InterviewQuestionModel) {
        viewModelScope.launch {
            insertInterviewQuestionUseCase.invoke(questionModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        interviewQuestionsBaseUIState.value =
                            interviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = true,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = false,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        interviewQuestionsBaseUIState.value =
                            interviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = true,
                                isFailMessage = ErrorMessages.ERROR,
                                isFailReason = Exception(result.message),
                            )

                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            interviewQuestionsBaseUIState.value =
                                interviewQuestionsBaseUIState.value.copy(
                                    data = null,
                                    list = listOf(),
                                    isLoading = false,
                                    isSuccess = true,
                                    isSuccessMessage = result.data,
                                    isFail = false,
                                    isFailMessage = null,
                                    isFailReason = null,
                                )
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
                        savedInterviewQuestionsBaseUIState.value =
                            savedInterviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = true,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = false,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        savedInterviewQuestionsBaseUIState.value =
                            savedInterviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = true,
                                isFailMessage = ErrorMessages.ERROR,
                                isFailReason = Exception(result.message),
                            )

                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            savedInterviewQuestionsBaseUIState.value =
                                savedInterviewQuestionsBaseUIState.value.copy(
                                    data = null,
                                    list = result.data!!,
                                    isLoading = false,
                                    isSuccess = true,
                                    isSuccessMessage = null,
                                    isFail = false,
                                    isFailMessage = null,
                                    isFailReason = null,
                                )
                    }
                }
            }
        }
    }

    fun saveInterviewQuestion(questionModel: InterviewQuestionModel) {
        viewModelScope.launch {
            saveInterviewQuestionUseCase.invoke(questionModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        savedInterviewQuestionsBaseUIState.value =
                            savedInterviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = true,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = false,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        savedInterviewQuestionsBaseUIState.value =
                            savedInterviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = true,
                                isFailMessage = ErrorMessages.ERROR,
                                isFailReason = Exception(result.message),
                            )

                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            savedInterviewQuestionsBaseUIState.value =
                                savedInterviewQuestionsBaseUIState.value.copy(
                                    data = null,
                                    list = listOf(),
                                    isLoading = false,
                                    isSuccess = true,
                                    isSuccessMessage = result.data,
                                    isFail = false,
                                    isFailMessage = null,
                                    isFailReason = null,
                                )
                    }
                }
            }
        }
    }

    fun removeSavedInterviewQuestion(questionModel: InterviewQuestionModel) {
        viewModelScope.launch {
            removeSavedInterviewQuestionUseCase.invoke(questionModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        savedInterviewQuestionsBaseUIState.value =
                            savedInterviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = true,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = false,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        savedInterviewQuestionsBaseUIState.value =
                            savedInterviewQuestionsBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = false,
                                isSuccessMessage = null,
                                isFail = true,
                                isFailMessage = ErrorMessages.ERROR,
                                isFailReason = Exception(result.message),
                            )

                    }

                    Status.SUCCESS -> {
                        if (result.data != null)
                            savedInterviewQuestionsBaseUIState.value =
                                savedInterviewQuestionsBaseUIState.value.copy(
                                    data = null,
                                    list = listOf(),
                                    isLoading = false,
                                    isSuccess = true,
                                    isSuccessMessage = result.data,
                                    isFail = false,
                                    isFailMessage = null,
                                    isFailReason = null,
                                )
                    }
                }
            }
        }
    }
}