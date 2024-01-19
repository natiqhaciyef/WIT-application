package com.natiqhaciyef.domain.domain.repository.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.data.source.FirebaseDataSource
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl(
    val ds: FirebaseDataSource
) : FirebaseRepository {
    override fun auth(): FirebaseAuth = ds.auth

    override fun storage(): FirebaseStorage = ds.storage

    override fun firestore(): FirebaseFirestore = ds.firestore

    override fun signInUser(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        ds.auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    override fun createAccount(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        ds.auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    override fun resetPasswordFromEmail(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        ds.auth.sendPasswordResetEmail(email)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }

    override fun updatePassword(
        newPassword: String,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        ds.auth.currentUser?.updatePassword(newPassword)
            ?.addOnSuccessListener {
                onSuccess()
            }?.addOnFailureListener(onFail)
    }


    override fun signOut() {
        ds.auth.signOut()
    }

}