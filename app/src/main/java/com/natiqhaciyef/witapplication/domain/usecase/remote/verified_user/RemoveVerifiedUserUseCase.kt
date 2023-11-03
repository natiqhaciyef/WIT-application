package com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user

import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.witapplication.data.models.VerifiedUserModel
import com.natiqhaciyef.witapplication.domain.repository.VerifiedUserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveVerifiedUserUseCase @Inject constructor(
    private val repository: VerifiedUserRepository,
) {

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val result = repository.removeVerifiedUser(id)
        if (result.success > 0) {
            emit(Resource.success(result.message))
        } else {
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }
    }
}