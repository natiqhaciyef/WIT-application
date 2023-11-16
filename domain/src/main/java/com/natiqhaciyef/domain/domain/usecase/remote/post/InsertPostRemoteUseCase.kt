package com.natiqhaciyef.domain.domain.usecase.remote.post

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.util.common.mappers.toPost
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertPostRemoteUseCase @Inject constructor(
    private val repository: PostRepository,
) {

    suspend operator fun invoke(postModel: MappedPostModel) = flow {
        emit(Resource.loading(null))

        val unMappedPost = postModel.toPost()
        val result = unMappedPost?.let { repository.insertPost(postModel = it) }
        if (result != null && result.success > 0) {
            emit(Resource.success(result.message))
        } else {
            emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
        }

    }

}