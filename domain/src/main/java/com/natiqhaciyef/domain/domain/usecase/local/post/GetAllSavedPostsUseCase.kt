package com.natiqhaciyef.domain.domain.usecase.local.post

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.util.common.mappers.toMappedPost
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllSavedPostsUseCase @Inject constructor(
    repository: PostRepository
): BaseUseCase<PostRepository>(repository) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))
        val result = repository.getAllSavedPosts()

        if (result != null) {
            val mappedList = mutableListOf<MappedPostModel>()
            for (post in result) {
                post.toMappedPost()?.let { mappedList.add(it) }
            }

            emit(Resource.success(mappedList))
        } else {
            emit(Resource.error(ConfigUseCase.LOADING_FAIL, null))
        }
    }
}