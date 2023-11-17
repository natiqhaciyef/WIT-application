package com.natiqhaciyef.domain.domain.usecase.remote.user

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.UserRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserByEmailUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(email: String) = flow {
        emit(Resource.loading(null))
        if (
            email.isNotEmpty() &&
            email.endsWith("@gmail.com")
        ) {
            val result = userRepository.getUserByEmail(email)

            if (result.userResult != null) {
                emit(Resource.success(result.userResult?.filter { it.email == email }))
            } else {
                emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
            }
        } else {
            emit(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }

    }
}