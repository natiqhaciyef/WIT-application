package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import javax.inject.Inject

class ResetPasswordUserUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {


    operator fun invoke(
        email: String,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        repository.resetPasswordFromEmail(
            email = email,
            onSuccess = onSuccess,
            onFail = onFail
        )
    }
}