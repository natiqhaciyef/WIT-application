package com.natiqhaciyef.domain.domain.usecase.local.post

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.util.common.mappers.toPost
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveSavedPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
) {

    suspend operator fun invoke(postModel: MappedPostModel) = flow {
        emit(Resource.loading(null))
        if (
            postModel.title.isNotEmpty() &&
            postModel.publishDate.isNotEmpty() &&
            postModel.user.email.isNotEmpty() &&
            postModel.user.name.isNotEmpty()
        ) {
            val post = postModel.toPost()

            post?.let { postRepository.removeSavedPost(it) }
            emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
        } else {
            emit(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
    }
}