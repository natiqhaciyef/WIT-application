package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.domain.domain.usecase.firebase.USER_DIRECTORY
import com.natiqhaciyef.util.common.mappers.toFirebaseMap
import com.natiqhaciyef.util.models.UserModel
import javax.inject.Inject

class CreateUserAccountUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        repository.createAccount(user, {
            insertUserToFirestore(
                userModel = user,
                onSuccess = onSuccess,
                onFail = onFail
            )
        }, onFail)
    }

    private fun insertUserToFirestore(
        userModel: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        val user = userModel.toFirebaseMap()
        repository.firestore()
            .collection(USER_DIRECTORY)
            .document(userModel.email)
            .set(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }
}