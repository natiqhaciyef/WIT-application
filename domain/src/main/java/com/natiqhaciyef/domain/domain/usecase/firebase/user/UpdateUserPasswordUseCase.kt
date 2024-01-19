package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.domain.domain.usecase.firebase.USER_DIRECTORY
import com.natiqhaciyef.util.common.mappers.toFirebaseMap
import com.natiqhaciyef.util.common.mappers.toUserModel
import javax.inject.Inject

class UpdateUserPasswordUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        newPassword: String,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        repository.updatePassword(newPassword, {
            updatePassword(newPassword, onSuccess, onFail)
        }, onFail)
    }

    private fun updatePassword(
        newPassword: String,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        val email = repository.auth().currentUser?.email
        repository.firestore().collection(USER_DIRECTORY)
            .document("$email")
            .get()
            .addOnSuccessListener {
                val user = it.toUserModel()
                user.password = newPassword
                repository.firestore().collection(USER_DIRECTORY)
                    .document("$email")
                    .update(user.toFirebaseMap())
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener(onFail)
            }
            .addOnFailureListener(onFail)
    }
}