package com.natiqhaciyef.domain.domain.usecase.remote.verified_user

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.VerifiedUserRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVerifiedUserByEmailUseCase @Inject constructor(
    private val repository: VerifiedUserRepository,
) {

    suspend operator fun invoke(email: String) = flow {
        emit(Resource.loading(null))
        if (email.isNotEmpty() &&
            email.endsWith("@gmail.com")
        ) {
            val result = repository.getVerifiedUserByEmail(email).verifiedUserResult
            if (result != null) {
                emit(Resource.success(result.filter { it.email == email }))
            } else {
                emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
            }
        } else {
            emit(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }
    }
}