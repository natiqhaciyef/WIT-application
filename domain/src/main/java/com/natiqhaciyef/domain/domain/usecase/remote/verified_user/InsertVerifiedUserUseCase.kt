package com.natiqhaciyef.domain.domain.usecase.remote.verified_user

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.VerifiedUserRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.VerifiedUserModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertVerifiedUserUseCase @Inject constructor(
    private val repository: VerifiedUserRepository,
) {

    suspend operator fun invoke(verifiedUserModel: VerifiedUserModel) = flow {
        emit(Resource.loading(null))

        if (
            verifiedUserModel.email.isNotEmpty() &&
            verifiedUserModel.email.endsWith("@gmail.com") &&
            verifiedUserModel.name.isNotEmpty() &&
            verifiedUserModel.password.isNotEmpty() &&
            verifiedUserModel.password.length > 7 &&
            verifiedUserModel.idImage.isNotEmpty() &&
            verifiedUserModel.image.isNotEmpty() &&
            verifiedUserModel.phone.isNotEmpty() &&
            verifiedUserModel.type.isNotEmpty()
        ) {
            val result = repository.insertVerifiedUser(verifiedUserModel)
            if (result.success > 0) {
                emit(Resource.success(result.message))
            } else {
                emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
            }
        } else {
            emit(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }
    }
}