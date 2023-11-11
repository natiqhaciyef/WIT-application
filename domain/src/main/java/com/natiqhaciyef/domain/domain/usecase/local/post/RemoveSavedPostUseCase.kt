package com.natiqhaciyef.domain.domain.usecase.local.post

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.util.common.mappers.toPost
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveSavedPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
) {

    suspend operator fun invoke(postModel: MappedPostModel) = flow {
        emit(Resource.loading(null))
        val post = postModel.toPost()

        postRepository.removeSavedPost(post)
        emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }
}