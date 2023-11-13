package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.common.Status
import com.natiqhaciyef.domain.domain.usecase.remote.exam.GetAllExamsUseCase
import com.natiqhaciyef.util.models.ExamModel
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
    private val getAllExamsUseCase: GetAllExamsUseCase
): BaseViewModel() {
    val timerState = mutableStateOf(0)
    val examState = mutableStateOf(UIState<MappedExamModel>())

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
        viewModelScope.launch {
            timerFlow.collectLatest { timer ->
                timerState.value = timer
            }
        }
    }

    private fun getAllExams(){
        viewModelScope.launch {
            getAllExamsUseCase.invoke().collectLatest { result ->
                when(result.status){
                    Status.SUCCESS -> {
                        if (result.data != null)
                            examState.value = examState.value.copy(isLoading = false, list = result.data!!)
                    }
                    Status.ERROR -> {
                        examState.value = examState.value.copy(isLoading = false, message = result.message)
                    }
                    Status.LOADING -> {
                        examState.value = examState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}