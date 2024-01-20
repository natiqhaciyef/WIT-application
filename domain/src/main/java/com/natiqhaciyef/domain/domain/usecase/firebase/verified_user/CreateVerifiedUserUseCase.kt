package com.natiqhaciyef.domain.domain.usecase.firebase.verified_user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.base.VERIFIED_USER_DIRECTORY
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.util.common.mappers.toFirebaseMap
import com.natiqhaciyef.util.models.VerifiedUserModel
import javax.inject.Inject

class CreateVerifiedUserUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        verifiedUser: VerifiedUserModel,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        val user = verifiedUser.toFirebaseMap()
        repository.firestore()
            .collection(VERIFIED_USER_DIRECTORY)
            .document(verifiedUser.email)
            .set(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)

    }

}