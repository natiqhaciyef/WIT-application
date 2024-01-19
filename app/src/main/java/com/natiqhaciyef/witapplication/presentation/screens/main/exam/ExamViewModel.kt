package com.natiqhaciyef.witapplication.presentation.screens.main.exam

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.common.Status
import com.natiqhaciyef.domain.domain.usecase.local.exam.GetAllParticipatedExamsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.exam.ParticipateExamUseCase
import com.natiqhaciyef.domain.domain.usecase.local.exam.RemoveExamParticipationUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.exam.GetAllExamsUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.exam.InsertExamUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamViewModel @Inject constructor(
    private val getAllExamsUseCase: GetAllExamsUseCase,
    private val insertExamUseCase: InsertExamUseCase,
    private val getAllParticipatedExamsUseCase: GetAllParticipatedExamsUseCase,
    private val participateExamUseCase: ParticipateExamUseCase,
    private val removeExamParticipationUseCase: RemoveExamParticipationUseCase,
) : BaseViewModel() {
    val timerState = mutableStateOf(0)
    val examBaseUIState = mutableStateOf(BaseUIState<MappedExamModel>())
    val participatedExamBaseUIState = mutableStateOf(BaseUIState<MappedExamModel>())

    init {
        getAllParticipatedExam()
        getAllExams()
        invokeTimer()
    }

    private fun getAllExams() {
        viewModelScope.launch {
            getAllExamsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            examBaseUIState.value = examBaseUIState.value.copy(
                                data = null,
                                list = result.data!!,
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = SuccessMessages.SUCCESS,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        examBaseUIState.value = examBaseUIState.value.copy(
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

                    Status.LOADING -> {
                        examBaseUIState.value = examBaseUIState.value.copy(
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
                }
            }
        }
    }

    fun insertExam(mappedExamModel: MappedExamModel) {
        viewModelScope.launch {
            insertExamUseCase.invoke(mappedExamModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            examBaseUIState.value = examBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = result.message,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        examBaseUIState.value = examBaseUIState.value.copy(
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

                    Status.LOADING -> {
                        examBaseUIState.value = examBaseUIState.value.copy(
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
                }
            }
        }
    }

    private fun invokeTimer(initTime: Int = 1200) {
        viewModelScope.launch {
            flow {
                var time = initTime
                while (time > 0) {
                    delay(1000)
                    emit(time)
                    time -= 1
                }
            }.collectLatest { second ->
                timerState.value = second
            }
        }
    }

    private fun getAllParticipatedExam() {
        viewModelScope.launch {
            getAllParticipatedExamsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
                                data = null,
                                list = result.data!!,
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = SuccessMessages.SUCCESS,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
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

                    Status.LOADING -> {
                        participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
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
                }
            }
        }
    }

    fun participateExam(examModel: MappedExamModel) {
        viewModelScope.launch {
            participateExamUseCase.invoke(examModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = result.message,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
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

                    Status.LOADING -> {
                        participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
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
                }
            }
        }
    }

    fun removeExamParticipation(examModel: MappedExamModel) {
        viewModelScope.launch {
            removeExamParticipationUseCase.invoke(examModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
                                data = null,
                                list = listOf(),
                                isLoading = false,
                                isSuccess = true,
                                isFail = false,
                                isSuccessMessage = result.message,
                                isFailMessage = null,
                                isFailReason = null,
                            )
                    }

                    Status.ERROR -> {
                        participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
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

                    Status.LOADING -> {
                        participatedExamBaseUIState.value = participatedExamBaseUIState.value.copy(
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
                }
            }
        }
    }

}