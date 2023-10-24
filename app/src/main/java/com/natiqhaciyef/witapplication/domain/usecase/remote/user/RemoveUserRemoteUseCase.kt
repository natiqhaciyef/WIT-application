package com.natiqhaciyef.witapplication.domain.usecase.remote.user

import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveUserRemoteUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val result = repository.removeUser(id)
        if (result.success > 0){
            emit(Resource.success(result))
        }else{
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }
    }
}