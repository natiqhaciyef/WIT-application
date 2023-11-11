package com.natiqhaciyef.domain.domain.usecase.remote.post

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.PostRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemovePostRemoteUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val result = repository.removePost(id)
        if (result.success > 0){
            emit(Resource.success(result.message))
        }else{
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }

    }

}