package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.domain.domain.usecase.firebase.USER_DIRECTORY
import com.natiqhaciyef.util.common.mappers.toFirebaseMap
import com.natiqhaciyef.util.models.UserModel
import javax.inject.Inject

class ChangeAccountPasswordUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        userModel: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit,
    ) {
        repository.firestore().collection(USER_DIRECTORY)
            .document(userModel.email)
            .update(userModel.toFirebaseMap())
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }
}