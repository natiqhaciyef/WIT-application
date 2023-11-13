package com.natiqhaciyef.domain.domain.usecase.remote.post

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.util.common.mappers.toMappedPost
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllPostRemoteUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        if (repository.getAllPosts().postResult != null) {
            val mappedPosts = mutableListOf<MappedPostModel>()
            for (post in repository.getAllPosts().postResult!!) {
                mappedPosts.add(post.toMappedPost())
            }
            emit(Resource.success(mappedPosts))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}