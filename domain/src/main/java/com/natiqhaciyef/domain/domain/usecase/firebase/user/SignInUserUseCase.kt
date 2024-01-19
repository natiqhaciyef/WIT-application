package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.UserModel
import javax.inject.Inject

class SignInUserUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        user: UserModel,
        onSuccess: () -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        if (user.email.isNotEmpty() && user.password.isNotEmpty())
            repository.signInUser(
                user = user,
                onSuccess = onSuccess,
                onFail = onFail
            )
        else
            onFail(Exception(ErrorMessages.EMPTY_FIELD))
    }
}