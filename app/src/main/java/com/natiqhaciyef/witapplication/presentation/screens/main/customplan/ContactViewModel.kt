package com.natiqhaciyef.witapplication.presentation.screens.main.customplan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.common.Status
import com.natiqhaciyef.domain.domain.usecase.local.contact.GetAllSavedContactsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.contact.RemoveSavedContactUseCase
import com.natiqhaciyef.domain.domain.usecase.local.contact.SaveContactUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.contact.GetAllContactUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.contact.InsertContactUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.contact.RemoveContactUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getAllContactUseCase: GetAllContactUseCase,
    private val insertContactUseCase: InsertContactUseCase,
    private val removeContactUseCase: RemoveContactUseCase,
    private val getAllSavedContactsUseCase: GetAllSavedContactsUseCase,
    private val removeSavedContactUseCase: RemoveSavedContactUseCase,
    private val saveContactUseCase: SaveContactUseCase,
) : BaseViewModel() {
    val contactBaseUIState = mutableStateOf(BaseUIState<ContactModel>())
    val savedContactBaseUIState = mutableStateOf(BaseUIState<ContactModel>())

    init {
        getAllContactRemote()
        getAllSavedContactLocal()
    }

    private fun getAllContactRemote() {
        viewModelScope.launch {
            getAllContactUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            contactBaseUIState.value = contactBaseUIState.value.copy(
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
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = false,
                            isFail = true,
                            isSuccessMessage = null,
                            isFailMessage = ErrorMessages.ERROR,
                            isFailReason = Exception(result.message),
                        )
                    }

                    Status.LOADING -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = true,
                            isSuccess = false,
                            isFail = false,
                            isSuccessMessage = null,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                    }
                }
            }
        }
    }

    private fun getAllSavedContactLocal() {
        viewModelScope.launch {
            getAllSavedContactsUseCase.invoke().collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null)
                            savedContactBaseUIState.value = savedContactBaseUIState.value.copy(
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
                        savedContactBaseUIState.value = savedContactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = false,
                            isFail = true,
                            isSuccessMessage = null,
                            isFailMessage = ErrorMessages.ERROR,
                            isFailReason = Exception(result.message),
                        )
                    }

                    Status.LOADING -> {
                        savedContactBaseUIState.value = savedContactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = true,
                            isSuccess = false,
                            isFail = false,
                            isSuccessMessage = null,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                    }
                }
            }
        }
    }


    fun insertContactRemote(
        contactModel: ContactModel,
        onSuccess: () -> Unit = {},
    ) {
        viewModelScope.launch {
            insertContactUseCase.invoke(contactModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = true,
                            isFail = false,
                            isSuccessMessage = result.data,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                        onSuccess()
                    }

                    Status.ERROR -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = false,
                            isFail = true,
                            isSuccessMessage = null,
                            isFailMessage = ErrorMessages.ERROR,
                            isFailReason = Exception(result.message),
                        )
                    }

                    Status.LOADING -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = true,
                            isSuccess = false,
                            isFail = false,
                            isSuccessMessage = null,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                    }
                }
            }
        }
    }

    fun saveContactLocal(
        contactModel: ContactModel,
        onSuccess: () -> Unit = {},
        onSuccessComposable: @Composable () -> Unit = {},
    ) {
        viewModelScope.launch {
            saveContactUseCase.invoke(contactModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        savedContactBaseUIState.value = savedContactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = true,
                            isFail = false,
                            isSuccessMessage = result.data,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                        onSuccess()
                    }

                    Status.ERROR -> {
                        savedContactBaseUIState.value = savedContactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = false,
                            isFail = true,
                            isSuccessMessage = null,
                            isFailMessage = ErrorMessages.ERROR,
                            isFailReason = Exception(result.message),
                        )
                    }

                    Status.LOADING -> {
                        savedContactBaseUIState.value = savedContactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = true,
                            isSuccess = false,
                            isFail = false,
                            isSuccessMessage = null,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                    }
                }
            }
        }
    }


    fun removeContactRemote(id: Int) {
        viewModelScope.launch {
            removeContactUseCase.invoke(id).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = true,
                            isFail = false,
                            isSuccessMessage = result.data,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                    }

                    Status.ERROR -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = false,
                            isFail = true,
                            isSuccessMessage = null,
                            isFailMessage = ErrorMessages.ERROR,
                            isFailReason = Exception(result.message),
                        )
                    }

                    Status.LOADING -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = true,
                            isSuccess = false,
                            isFail = false,
                            isSuccessMessage = null,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                    }
                }
            }
        }
    }

    fun removeContactLocal(contactModel: ContactModel) {
        viewModelScope.launch {
            removeSavedContactUseCase.invoke(contactModel).collectLatest { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = true,
                            isFail = false,
                            isSuccessMessage = result.data,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                    }

                    Status.ERROR -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = false,
                            isSuccess = false,
                            isFail = true,
                            isSuccessMessage = null,
                            isFailMessage = ErrorMessages.ERROR,
                            isFailReason = Exception(result.message),
                        )
                    }

                    Status.LOADING -> {
                        contactBaseUIState.value = contactBaseUIState.value.copy(
                            data = null,
                            list = listOf(),
                            isLoading = true,
                            isSuccess = false,
                            isFail = false,
                            isSuccessMessage = null,
                            isFailMessage = null,
                            isFailReason = null,
                        )
                    }
                }
            }
        }
    }
}