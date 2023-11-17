package com.natiqhaciyef.witapplication.domain_module_test.repository

import com.natiqhaciyef.domain.domain.repository.VerifiedUserRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.UserResult
import com.natiqhaciyef.util.models.result.VerifiedUserResult

class FakeVerifiedUserRepository(
    private val list: MutableList<VerifiedUserModel>,
) : VerifiedUserRepository {

    private val remoteUsersResult = VerifiedUserResult(list)

    override suspend fun getAllVerifiedUsers(): VerifiedUserResult {
        return remoteUsersResult
    }

    override suspend fun getVerifiedUserByEmail(email: String): VerifiedUserResult {
        return remoteUsersResult
    }

    override suspend fun insertVerifiedUser(verifiedUserModel: VerifiedUserModel): CRUDResponse {
        if (remoteUsersResult.verifiedUserResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val list = remoteUsersResult.verifiedUserResult!!.toMutableList()
        list.add(verifiedUserModel)
        remoteUsersResult.verifiedUserResult = list

        return if (remoteUsersResult.verifiedUserResult!!.contains(verifiedUserModel))
            CRUDResponse(success = 1, message = BaseUseCase.INSERT_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.INSERT_FAIL)
    }

    override suspend fun updateVerifiedUser(verifiedUserModel: VerifiedUserModel): CRUDResponse {
        if (remoteUsersResult.verifiedUserResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        remoteUsersResult.verifiedUserResult!!.find { it.id == verifiedUserModel.id }.apply {
            this?.email = verifiedUserModel.email
            this?.name = verifiedUserModel.name
            this?.password = verifiedUserModel.password
            this?.image = verifiedUserModel.image
            this?.idImage = verifiedUserModel.idImage
            this?.phone = verifiedUserModel.phone
            this?.type = verifiedUserModel.type
        }
        remoteUsersResult.verifiedUserResult = list

        return if (remoteUsersResult.verifiedUserResult!!.contains(verifiedUserModel))
            CRUDResponse(success = 1, message = BaseUseCase.UPDATE_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.UPDATE_FAIL)
    }

    override suspend fun removeVerifiedUser(id: Int): CRUDResponse {
        if (remoteUsersResult.verifiedUserResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val element = remoteUsersResult.verifiedUserResult!!.find { it.id == id }
        val list = remoteUsersResult.verifiedUserResult!!.toMutableList()
        list.remove(element)
        remoteUsersResult.verifiedUserResult = list

        return if (!remoteUsersResult.verifiedUserResult!!.any { it.id == id })
            CRUDResponse(success = 1, message = BaseUseCase.REMOVE_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.REMOVE_FAIL)
    }
}