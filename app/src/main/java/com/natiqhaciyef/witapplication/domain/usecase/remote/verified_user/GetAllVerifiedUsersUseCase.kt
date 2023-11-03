package com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user

import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.witapplication.domain.repository.VerifiedUserRepository
import com.natiqhaciyef.witapplication.domain.repository.impl.VerifiedUserRepositoryImpl
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllVerifiedUsersUseCase @Inject constructor(
    private val repository: VerifiedUserRepository,
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        val result = repository.getAllVerifiedUsers().verifiedUserResult
        if (result != null) {
            emit(Resource.success(result))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}