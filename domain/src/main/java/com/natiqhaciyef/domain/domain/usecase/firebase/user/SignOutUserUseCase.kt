package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.base.USER_DIRECTORY
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import javax.inject.Inject

class SignOutUserUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        repository.signOut()
//        removeUser(
//            onSuccess = {
//                onSuccess()
//            },
//            onFail = onFail
//        )
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