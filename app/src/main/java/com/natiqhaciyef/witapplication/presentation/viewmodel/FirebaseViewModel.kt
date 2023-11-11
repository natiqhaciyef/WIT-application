package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.data.models.MaterialModel
import com.natiqhaciyef.domain.domain.repository.impl.FirebaseRepositoryImpl
import com.natiqhaciyef.domain.domain.usecase.firebase.GetAllFAQUseCase
import com.natiqhaciyef.domain.domain.usecase.firebase.GetAllMaterialsNameUseCase
import com.natiqhaciyef.domain.domain.usecase.firebase.GetAllNotificationsUseCase
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.service.InfoModel
import com.natiqhaciyef.util.models.service.NotificationModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepositoryImpl,
    private val getAllMaterialsNameUseCase: GetAllMaterialsNameUseCase,
    private val getAllFAQUseCase: GetAllFAQUseCase,
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
) : BaseViewModel() {
    val faqState = mutableStateOf(UIState<InfoModel>())
    val filesState = mutableStateOf(UIState<MaterialModel>())
    val notificationState = mutableStateOf(UIState<NotificationModel>())

    init {
        getAllFAQ()
        getAllNotification()
    }

    fun signInUser(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    ) {
        viewModelScope.launch {
            firebaseRepository.signInUser(
                user = user,
                onSuccess = onSuccess,
                onFail = onFail
            )

        }
    }

    fun createAccount(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    ) {
        viewModelScope.launch {
            firebaseRepository.createAccount(
                user = user,
                onSuccess = onSuccess,
                onFail = onFail
            )
        }
    }

    fun resetPasswordFromEmail(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    ) {
        viewModelScope.launch {
            firebaseRepository.resetPasswordFromEmail(
                email = email,
                onSuccess = onSuccess,
                onFail = onFail
            )
        }
    }

    fun updatePassword(
        user: UserModel,
    ) {
        viewModelScope.launch {
            firebaseRepository.updatePassword(user)
        }
    }


    fun signOut() {
        viewModelScope.launch {
            firebaseRepository.signOut()
        }
    }

    private fun getAllFAQ() {
        getAllFAQUseCase.invoke(
            onSuccess = { result ->
                if (result.isNotEmpty())
                    faqState.value = faqState.value.copy(
                        list = result
                    )
            },
            onFail = { error ->
                faqState.value = faqState.value.copy(
                    message = error?.localizedMessage
                )
            }
        )
    }

    // firebase state holding
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
                        isLoading = false
                    )
                },
                onFail = { message ->
                    filesState.value = filesState.value.copy(
                        message = message?.localizedMessage,
                        isLoading = false
                    )
                },
            )
        }
    }

    private fun getAllNotification() {
        viewModelScope.launch {
            getAllNotificationsUseCase.invoke(
                onSuccess = { notifications ->
                    notificationState.value = notificationState.value.copy(
                        list = notifications,
                        isLoading = false
                    )
                },
                onFail = { exception ->
                    notificationState.value = notificationState.value.copy(
                        message = exception?.localizedMessage,
                        isLoading = false
                    )
                }
            )
        }
    }
}