package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.domain.domain.usecase.firebase.USER_DIRECTORY
import com.natiqhaciyef.util.common.mappers.toUserModel
import javax.inject.Inject

class GetUserByEmailUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(email: String) =
        repository.firestore().collection(USER_DIRECTORY)
            .document(email)
            .get()
            .result
            .toUserModel()
}