package com.natiqhaciyef.witapplication.domain.usecase.local.post

import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.witapplication.common.mappers.toPost
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel
import com.natiqhaciyef.witapplication.domain.repository.PostRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateSavedPostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(postModel: MappedPostModel) = flow {
        emit(Resource.loading(null))
        val post = postModel.toPost()

        postRepository.insertPost(post)
        emit(Resource.success(BaseUseCase.UPDATE_SUCCESS))
    }
}