package com.natiqhaciyef.witapplication.domain.repository

import com.natiqhaciyef.witapplication.data.models.UserModel

interface FirebaseRepository {
    suspend fun signInUser(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    )

    suspend fun createAccount(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    )

    suspend fun resetPasswordFromEmail(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception) -> Unit
    )

    suspend fun updatePassword(
        user: UserModel,
    )

    suspend fun signOut()
}