package com.natiqhaciyef.witapplication.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.witapplication.data.models.InfoModel
import com.natiqhaciyef.witapplication.data.models.MaterialModel
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.domain.repository.impl.FirebaseRepositoryImpl
import com.natiqhaciyef.witapplication.domain.usecase.firebase.GetAllFAQUseCase
import com.natiqhaciyef.witapplication.domain.usecase.firebase.GetAllMaterialsNameUseCase
import com.natiqhaciyef.witapplication.domain.usecase.firebase.GetMaterialUseCase
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepositoryImpl,
    private val getAllMaterialsNameUseCase: GetAllMaterialsNameUseCase,
    private val getMaterialUseCase: GetMaterialUseCase,
    private val getAllFAQUseCase: GetAllFAQUseCase,
) : BaseViewModel() {
    val faqState = mutableStateOf(UIState<InfoModel>())
    val filesState = mutableStateOf(UIState<MaterialModel>())

    init {
        getAllFAQ()
    }

    fun getUser() = Firebase.auth

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
                    faqState.value = faqState.value.copy(list = result)
            },
            onFail = { error ->
                if (error != null)
                    faqState.value = faqState.value.copy(message = error.localizedMessage)
            }
        )

    }

    fun getAllMaterials(
        field: String,
    ) {

        getAllMaterialsNameUseCase.invoke(
            concept = field,
            onSuccess = { files ->

                filesState.value = filesState.value.copy(list = files)
            },
            onFail = { }
        )
    }

}