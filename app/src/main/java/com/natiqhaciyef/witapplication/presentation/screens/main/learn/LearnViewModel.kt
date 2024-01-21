package com.natiqhaciyef.witapplication.presentation.screens.main.learn

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.models.MaterialModel
import com.natiqhaciyef.domain.domain.usecase.firebase.GetAllFAQUseCase
import com.natiqhaciyef.domain.domain.usecase.firebase.GetAllMaterialsNameUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.util.models.service.InfoLangModel
import com.natiqhaciyef.util.models.service.InfoModel
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(
    private val getAllFAQUseCase: GetAllFAQUseCase
) : BaseViewModel() {
    val faqState = mutableStateOf(BaseUIState<InfoLangModel>())

    init {
        getAllFAQ()
    }

    private fun getAllFAQ() {
        getAllFAQUseCase.invoke(
            onSuccess = { result ->
                if (result.isNotEmpty())
                    faqState.value = faqState.value.copy(
                        data = null,
                        list = result,
                        isLoading = false,
                        isSuccess = true,
                        isSuccessMessage = SuccessMessages.SUCCESS,
                        isFail = false,
                        isFailMessage = null,
                        isFailReason = null,
                    )
            },
            onFail = { error ->
                faqState.value = faqState.value.copy(
                    data = null,
                    list = listOf(),
                    isLoading = false,
                    isSuccess = false,
                    isSuccessMessage = null,
                    isFail = true,
                    isFailMessage = ErrorMessages.ERROR,
                    isFailReason = Exception(error?.localizedMessage),
                )
            }
        )
    }

}