package com.natiqhaciyef.domain.domain.usecase.remote.user

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveUserRemoteUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val result = repository.removeUser(id)
        if (result.success > 0){
            emit(Resource.success(result.message))
        }else{
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }
    }
}