package com.natiqhaciyef.witapplication.domain.repository.impl

import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.source.FirebaseDataSource
import com.natiqhaciyef.witapplication.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl(
    val ds: FirebaseDataSource
) : FirebaseRepository {

    override suspend fun signInUser(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        ds.auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    override suspend fun createAccount(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        ds.auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    override suspend fun resetPasswordFromEmail(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    ) {
        ds.auth.sendPasswordResetEmail(email)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    override suspend fun updatePassword(
        user: UserModel,
    ) {
        ds.auth.currentUser?.updatePassword(user.password)
    }


    override suspend fun signOut() {
        ds.auth.signOut()
    }

}