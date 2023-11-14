package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.common.Status
import com.natiqhaciyef.domain.domain.usecase.remote.exam.GetAllExamsUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.exam.InsertExamUseCase
import com.natiqhaciyef.util.models.QuestionModel
import com.natiqhaciyef.util.models.enums.ExamLevels
import com.natiqhaciyef.util.models.enums.QuestionCategories
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
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
) : BaseViewModel() {
    val timerState = mutableStateOf(0)
    val examUIState = mutableStateOf(UIState<MappedExamModel>())

    private val timerFlow = flow {
        var time = 1200
        while (time > 0) {
            delay(1000)
            emit(time)
            time -= 1
        }
    }

    init {
        getAllExams()
        invokeTimer()
    }

    private fun getAllExams() {
        viewModelScope.launch {
            getAllExamsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            examUIState.value =
                                examUIState.value.copy(isLoading = false, list = result.data!!)
                    }

                    Status.ERROR -> {
                        examUIState.value =
                            examUIState.value.copy(isLoading = false, message = result.message)
                    }

                    Status.LOADING -> {
                        examUIState.value = examUIState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun insertExam(mappedExamModel: MappedExamModel) {
        viewModelScope.launch {
            insertExamUseCase.invoke(mappedExamModel).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> {
                        examUIState.value = examUIState.value.copy(isLoading = true)
                    }

                    Status.SUCCESS -> {
                        examUIState.value =
                            examUIState.value.copy(isLoading = false, message = result.data)
                    }

                    Status.ERROR -> {
                        examUIState.value =
                            examUIState.value.copy(isLoading = false, message = result.message)
                    }
                }
            }
        }
    }

    fun invokeTimer() {
        viewModelScope.launch {
            timerFlow.collectLatest { timer ->
                timerState.value = timer
            }
        }
    }
}