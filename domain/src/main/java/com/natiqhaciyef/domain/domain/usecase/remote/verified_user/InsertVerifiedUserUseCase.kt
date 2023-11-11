package com.natiqhaciyef.domain.domain.usecase.remote.verified_user

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.VerifiedUserRepository
import com.natiqhaciyef.util.models.VerifiedUserModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertVerifiedUserUseCase @Inject constructor(
    private val repository: VerifiedUserRepository,
) {

    suspend operator fun invoke(verifiedUserModel: VerifiedUserModel) = flow {
        emit(Resource.loading(null))

        val result = repository.insertVerifiedUser(verifiedUserModel)
        if (result.success > 0) {
            emit(Resource.success(result.message))
        } else {
            emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
        }
    }
}