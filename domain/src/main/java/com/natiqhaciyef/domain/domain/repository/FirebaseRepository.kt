package com.natiqhaciyef.domain.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.natiqhaciyef.util.models.UserModel

interface FirebaseRepository {

    fun auth(): FirebaseAuth

    fun storage(): FirebaseStorage

    fun firestore(): FirebaseFirestore

    fun signInUser(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    )

    fun createAccount(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    )

    fun resetPasswordFromEmail(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    )

    fun updatePassword(user: UserModel)

    fun signOut()
}