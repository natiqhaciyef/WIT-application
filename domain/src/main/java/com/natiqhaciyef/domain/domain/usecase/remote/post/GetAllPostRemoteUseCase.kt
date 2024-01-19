package com.natiqhaciyef.domain.domain.usecase.remote.post

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.util.common.mappers.toMappedPost
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllPostRemoteUseCase @Inject constructor(
    repository: PostRepository
): BaseUseCase<PostRepository>(repository) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        if (repository.getAllPosts().postResult != null) {
            val mappedPosts = mutableListOf<MappedPostModel>()
            for (post in repository.getAllPosts().postResult!!) {
                post.toMappedPost()?.let { mappedPosts.add(it) }
            }
            emit(Resource.success(mappedPosts))
        } else {
            emit(Resource.error(ConfigUseCase.LOADING_FAIL, null))
        }
    }

}