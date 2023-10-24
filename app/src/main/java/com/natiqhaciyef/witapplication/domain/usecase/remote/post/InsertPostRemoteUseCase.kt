package com.natiqhaciyef.witapplication.domain.usecase.remote.post

import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.mappers.toPost
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel
import com.natiqhaciyef.witapplication.domain.repository.PostRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertPostRemoteUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postModel: MappedPostModel) = flow {
        emit(Resource.loading(null))

        val unMappedPost = postModel.toPost()
        val result = repository.insertPost(postModel = unMappedPost)
        if (result.success > 0){
            emit(Resource.success(result))
        }else{
            emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
        }

    }

}