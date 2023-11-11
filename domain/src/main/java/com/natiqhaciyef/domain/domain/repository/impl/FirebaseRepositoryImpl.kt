package com.natiqhaciyef.domain.domain.repository.impl

import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.data.source.FirebaseDataSource

class FirebaseRepositoryImpl(
    val ds: FirebaseDataSource
) {

    fun signInUser(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        ds.auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    fun createAccount(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        ds.auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    fun resetPasswordFromEmail(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        ds.auth.sendPasswordResetEmail(email)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    fun updatePassword(
        user: UserModel,
    ) {
        ds.auth.currentUser?.updatePassword(user.password)
    }


    fun signOut() {
        ds.auth.signOut()
    }

}