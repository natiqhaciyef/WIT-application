package com.natiqhaciyef.domain.domain.usecase.firebase.verified_user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.domain.domain.usecase.firebase.VERIFIED_USER_DIRECTORY
import com.natiqhaciyef.util.models.VerifiedUserModel
import javax.inject.Inject

class RemoveVerifiedUserUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        verifiedUser: VerifiedUserModel,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        repository.firestore().collection(VERIFIED_USER_DIRECTORY)
            .document(verifiedUser.email)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener(onFail)
    }
}