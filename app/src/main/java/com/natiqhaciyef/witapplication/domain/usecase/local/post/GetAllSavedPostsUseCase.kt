package com.natiqhaciyef.witapplication.domain.usecase.local.post

import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.witapplication.common.mappers.toMappedPost
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel
import com.natiqhaciyef.witapplication.domain.repository.PostRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllSavedPostsUseCase @Inject constructor(
    private val postRepository: PostRepository,
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))
        val result = postRepository.getAllSavedPosts()

        if (result != null) {
            val mappedList = mutableListOf<MappedPostModel>()
            for (post in result) {
                mappedList.add(post.toMappedPost())
            }

            emit(Resource.success(mappedList))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }
}