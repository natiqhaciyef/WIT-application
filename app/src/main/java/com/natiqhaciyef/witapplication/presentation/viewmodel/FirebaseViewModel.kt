package com.natiqhaciyef.witapplication.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : BaseViewModel() {

    fun signInUser(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
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
        onFail: (Exception) -> Unit
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
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            firebaseRepository.resetPasswordFromEmail(
                user = user,
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
}