package com.natiqhaciyef.domain.domain.usecase.remote.post

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.util.common.mappers.toPost
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdatePostRemoteUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postModel: MappedPostModel) = flow {
        emit(Resource.loading(null))

        val unMappedPost = postModel.toPost()
        val result = repository.updatePost(postModel = unMappedPost)
        if (result.success > 0){
            emit(Resource.success(result.message))
        }else{
            emit(Resource.error(BaseUseCase.UPDATE_FAIL, null))
        }

    }

}