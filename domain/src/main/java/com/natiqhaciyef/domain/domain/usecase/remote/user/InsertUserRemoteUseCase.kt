package com.natiqhaciyef.domain.domain.usecase.remote.user

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.UserRepository
import com.natiqhaciyef.util.models.UserModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertUserRemoteUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(userModel: UserModel) = flow {
        emit(Resource.loading(null))

        val result = repository.insertUser(userModel = userModel)
        if (result.success > 0){
            emit(Resource.success(result.message))
        }else{
            emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
        }
    }
}