package com.natiqhaciyef.domain.domain.usecase.remote.post

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemovePostRemoteUseCase @Inject constructor(
    repository: PostRepository
): BaseUseCase<PostRepository>(repository) {

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val result = repository.removePost(id)
        if (result.success > 0){
            emit(Resource.success(result.message))
        }else{
            emit(Resource.error(ConfigUseCase.REMOVE_FAIL, null))
        }

    }

}