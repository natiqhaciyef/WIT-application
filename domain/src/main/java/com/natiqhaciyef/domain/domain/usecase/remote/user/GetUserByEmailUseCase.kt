package com.natiqhaciyef.domain.domain.usecase.remote.user

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserByEmailUseCase @Inject constructor(
    private val userRepository: UserRepository
){

    suspend operator fun invoke(email: String) = flow{
        emit(Resource.loading(null))
        val result = userRepository.getUserByEmail(email)

        if (result.userResult != null){
            emit(Resource.success(result.userResult))
        }else{
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }

    }
}