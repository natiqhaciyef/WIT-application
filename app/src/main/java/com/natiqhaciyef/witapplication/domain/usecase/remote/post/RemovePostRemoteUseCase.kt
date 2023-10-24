package com.natiqhaciyef.witapplication.domain.usecase.remote.post

import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.domain.repository.PostRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemovePostRemoteUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val result = repository.removePost(id)
        if (result.success > 0){
            emit(Resource.success(result))
        }else{
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }

    }

}