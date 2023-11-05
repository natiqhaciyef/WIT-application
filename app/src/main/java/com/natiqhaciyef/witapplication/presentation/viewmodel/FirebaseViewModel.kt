package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.witapplication.data.models.service.InfoModel
import com.natiqhaciyef.witapplication.data.models.MaterialModel
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.models.service.NotificationModel
import com.natiqhaciyef.witapplication.domain.repository.impl.FirebaseRepositoryImpl
import com.natiqhaciyef.witapplication.domain.usecase.firebase.GetAllFAQUseCase
import com.natiqhaciyef.witapplication.domain.usecase.firebase.GetAllMaterialsNameUseCase
import com.natiqhaciyef.witapplication.domain.usecase.firebase.GetAllNotificationsUseCase
import com.natiqhaciyef.witapplication.domain.usecase.firebase.GetMaterialUseCase
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
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
        firebaseRepository.signInUser(
            user = user,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }

    fun createAccount(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    ) {
        firebaseRepository.createAccount(
            user = user,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }

    fun resetPasswordFromEmail(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    ) {
        firebaseRepository.resetPasswordFromEmail(
            email = email,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }

    fun updatePassword(
        user: UserModel,
    ) {
        firebaseRepository.updatePassword(user)
    }


    fun signOut() {
        firebaseRepository.signOut()
    }

    private fun getAllFAQ() {
        getAllFAQUseCase.invoke(
            onSuccess = { result ->
                if (result.isNotEmpty())
                    faqState.value.apply {
                        this.list = result
                    }
            },
            onFail = { error ->
                if (error != null)
                    faqState.value.apply {
                        this.message = error.localizedMessage
                    }
            }
        )
    }

    fun getAllMaterials(
        field: String,
    ) {
        getAllMaterialsNameUseCase.invoke(
            concept = field,
            onSuccess = { files ->
                filesState.value.apply {
                    this.list = files
                }
            },
            onFail = { }
        )
    }

    private fun getAllNotification() {
        getAllNotificationsUseCase.invoke(
            onSuccess = { notifications ->
                notificationState.value.apply {
                    list = notifications
                    isLoading = false
                }
            },
            onFail = { exception ->
                notificationState.value.apply {
                    message = exception?.message
                    isLoading = false
                }
            }
        )
    }
}