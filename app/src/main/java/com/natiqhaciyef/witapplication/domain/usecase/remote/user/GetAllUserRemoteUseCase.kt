package com.natiqhaciyef.witapplication.domain.usecase.remote.user

import com.natiqhaciyef.voyagersaz.common.Resource
import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.mappers.toMappedPost
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel
import com.natiqhaciyef.witapplication.domain.repository.PostRepository
import com.natiqhaciyef.witapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllUserRemoteUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        val result = repository.getAllUsers().userResult
        if (result != null) {
            emit(Resource.success(result))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }
}