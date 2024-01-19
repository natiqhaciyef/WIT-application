package com.natiqhaciyef.domain.domain.usecase.remote.post

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.util.common.mappers.toPost
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdatePostRemoteUseCase @Inject constructor(
    repository: PostRepository
): BaseUseCase<PostRepository>(repository) {

    suspend operator fun invoke(postModel: MappedPostModel) = flow {
        emit(Resource.loading(null))

        if (
            postModel.title.isNotEmpty() &&
            postModel.publishDate.isNotEmpty() &&
            postModel.user.email.isNotEmpty() &&
            postModel.user.name.isNotEmpty()
        ) {
            val unMappedPost = postModel.toPost()
            val result = unMappedPost?.let { repository.updatePost(postModel = it) }
            if (result != null && result.success > 0) {
                emit(Resource.success(result.message))
            } else {
                emit(Resource.error(ConfigUseCase.UPDATE_FAIL, null))
            }
        } else {
            emit(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }

    }

}