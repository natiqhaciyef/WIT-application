package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.witapplication.common.Status
import com.natiqhaciyef.witapplication.data.models.ContactModel
import com.natiqhaciyef.witapplication.domain.usecase.local.contact.GetAllSavedContactsUseCase
import com.natiqhaciyef.witapplication.domain.usecase.local.contact.RemoveSavedContactUseCase
import com.natiqhaciyef.witapplication.domain.usecase.local.contact.SaveContactUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.contact.GetAllContactUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.contact.InsertContactUseCase
import com.natiqhaciyef.witapplication.domain.usecase.remote.contact.RemoveContactUseCase
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
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
    val contactUIState = mutableStateOf(UIState<ContactModel>())
    val savedContactUIState = mutableStateOf(UIState<ContactModel>())

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
                            contactUIState.value.apply {
                                this.list = result.data
                                this.isLoading = false
                            }
                    }

                    Status.ERROR -> {
                        contactUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        contactUIState.value.isLoading = true
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
                            savedContactUIState.value.apply {
                                this.list = result.data
                                this.isLoading = false
                            }
                    }

                    Status.ERROR -> {
                        savedContactUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        savedContactUIState.value.isLoading = true
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
                        contactUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                        onSuccess()
                    }

                    Status.ERROR -> {
                        contactUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }

                    }

                    Status.LOADING -> {
                        contactUIState.value.isLoading = true
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
                        savedContactUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                    }

                    Status.ERROR -> {
                        savedContactUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        savedContactUIState.value.isLoading = true
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
                        contactUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                    }

                    Status.ERROR -> {
                        contactUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        contactUIState.value.isLoading = true
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
                        savedContactUIState.value.apply {
                            this.message = result.data
                            this.isLoading = false
                        }
                    }

                    Status.ERROR -> {
                        savedContactUIState.value.apply {
                            this.message = result.message
                            this.isLoading = false
                        }
                    }

                    Status.LOADING -> {
                        savedContactUIState.value.isLoading = true
                    }
                }
            }
        }
    }
}