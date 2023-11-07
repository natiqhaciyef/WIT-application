package com.natiqhaciyef.witapplication.domain.usecase.remote.post

import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.mappers.toMappedPost
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel
import com.natiqhaciyef.witapplication.domain.repository.PostRepository
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