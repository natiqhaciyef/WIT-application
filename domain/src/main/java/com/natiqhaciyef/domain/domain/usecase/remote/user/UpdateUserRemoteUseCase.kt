package com.natiqhaciyef.domain.domain.usecase.remote.user

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.UserRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.UserModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateUserRemoteUseCase @Inject constructor(
    private val repository: UserRepository,
) {

    suspend operator fun invoke(userModel: UserModel) = flow {
        emit(Resource.loading(null))
        if (
            userModel.email.isNotEmpty() &&
            userModel.email.endsWith("@gmail.com") &&
            userModel.name.isNotEmpty() &&
            userModel.password.length > 7
        ) {
            val result = repository.updateUser(userModel = userModel)
            if (result.success > 0) {
                emit(Resource.success(result.message))
            } else {
                emit(Resource.error(BaseUseCase.UPDATE_FAIL, null))
            }
        } else {
            emit(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }
    }
}