package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.domain.domain.usecase.firebase.USER_DIRECTORY
import javax.inject.Inject

class SignOutUserUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        removeUser(
            onSuccess = {
                onSuccess()
            },
            onFail = onFail
        )
    }

    private fun removeUser(
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        val email = repository.auth().currentUser?.email
        repository.firestore().collection(USER_DIRECTORY)
            .document("$email")
            .delete()
            .addOnSuccessListener {
                repository.signOut()
                onSuccess()
            }
            .addOnFailureListener(onFail)
    }
}