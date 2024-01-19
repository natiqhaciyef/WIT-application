package com.natiqhaciyef.witapplication.presentation.screens.main.materials

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.models.MaterialModel
import com.natiqhaciyef.domain.domain.usecase.firebase.GetAllMaterialsNameUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaterialsViewModel @Inject constructor(
    private val getAllMaterialsNameUseCase: GetAllMaterialsNameUseCase
):BaseViewModel() {
    val filesState = mutableStateOf(BaseUIState<MaterialModel>())

    fun getAllMaterials(
        field: String,
    ) {
        viewModelScope.launch {
            filesState.value.isLoading = true
            delay(500)
            getAllMaterialsNameUseCase.invoke(
                concept = field,
                onSuccess = { files ->
                    filesState.value = filesState.value.copy(
                        list = files,
                        isLoading = false,
                        data = null,
                        isSuccess = true,
                        isSuccessMessage = SuccessMessages.SUCCESS,
                        isFail = false,
                        isFailMessage = null,
                        isFailReason = null,
                    )
                },
                onFail = { message ->
                    filesState.value = filesState.value.copy(
                        isLoading = false,
                        data = null,
                        list = listOf(),
                        isSuccess = false,
                        isSuccessMessage = null,
                        isFail = true,
                        isFailMessage = ErrorMessages.ERROR,
                        isFailReason = Exception(message),
                    )
                },
            )
        }
    }
}