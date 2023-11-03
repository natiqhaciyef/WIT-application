package com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user

import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.witapplication.domain.repository.VerifiedUserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVerifiedUserByEmailUseCase @Inject constructor(
    private val repository: VerifiedUserRepository,
) {

    suspend operator fun invoke(email: String) = flow {
        emit(Resource.loading(null))

        val result = repository.getVerifiedUserByEmail(email).verifiedUserResult
        if (result != null) {
            emit(Resource.success(result))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }
}