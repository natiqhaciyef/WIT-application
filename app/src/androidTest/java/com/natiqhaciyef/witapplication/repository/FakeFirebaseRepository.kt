package com.natiqhaciyef.witapplication.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.UserModel

class FakeFirebaseRepository: FirebaseRepository{
    private val userState = mutableListOf<UserModel>()

    override fun auth(): FirebaseAuth = FirebaseAuth.getInstance()

    override fun storage(): FirebaseStorage = FirebaseStorage.getInstance()

    override fun firestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun signInUser(user: UserModel, onSuccess: () -> Unit, onFail: (Exception) -> Unit) {
        if (!userState.map { it.email }.contains(user.email)) {
            onFail(Exception(ErrorMessages.SIGN_IN_FAILED))
            return
        }else{
            onSuccess()
        }
    }

    override fun createAccount(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    ) {
        if (userState.map { it.email }.contains(user.email)) {
            onFail(Exception(ErrorMessages.SIGN_UP_FAILED))
            return
        }else{
            onSuccess()
        }
    }

    override fun resetPasswordFromEmail(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit,
    ) {
        if (!userState.map { it.email }.contains(email)) {
            onFail(Exception(ErrorMessages.PASSWORD_RESETTING_FAILED))
            return
        }else{
            onSuccess()
        }
    }

    override fun updatePassword(user: UserModel) {
        if (!userState.contains(user) && userState.map { it.id }.contains(user.id)) {
            return
        }
    }

    override fun signOut() {

    }
}