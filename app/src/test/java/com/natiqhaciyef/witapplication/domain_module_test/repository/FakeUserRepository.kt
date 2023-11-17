package com.natiqhaciyef.witapplication.domain_module_test.repository

import com.natiqhaciyef.domain.domain.repository.UserRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.PostResult
import com.natiqhaciyef.util.models.result.UserResult

class FakeUserRepository(
    private val list: MutableList<UserModel>
): UserRepository{

    private val remoteUsersResult = UserResult(list)

    override suspend fun getAllUsers(): UserResult {
        return remoteUsersResult
    }

    override suspend fun getUserByEmail(email: String): UserResult {
        return remoteUsersResult
    }

    override suspend fun insertUser(userModel: UserModel): CRUDResponse {
        if (remoteUsersResult.userResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val list = remoteUsersResult.userResult!!.toMutableList()
        list.add(userModel)
        remoteUsersResult.userResult = list

        return if (remoteUsersResult.userResult!!.contains(userModel))
            CRUDResponse(success = 1, message = BaseUseCase.INSERT_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.INSERT_FAIL)
    }

    override suspend fun updateUser(userModel: UserModel): CRUDResponse {
        if (remoteUsersResult.userResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        remoteUsersResult.userResult!!.find { it.id == userModel.id }.apply {
            this?.email = userModel.email
            this?.name = userModel.name
            this?.password = userModel.password
        }

        return if (remoteUsersResult.userResult!!.contains(userModel))
            CRUDResponse(success = 1, message = BaseUseCase.UPDATE_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.UPDATE_FAIL)
    }

    override suspend fun removeUser(id: Int): CRUDResponse {
        if (remoteUsersResult.userResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val list = remoteUsersResult.userResult!!.toMutableList()
        list.removeIf { it.id == id }
        remoteUsersResult.userResult = list

        return if (!remoteUsersResult.userResult!!.any { it.id == id })
            CRUDResponse(success = 1, message = BaseUseCase.REMOVE_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.REMOVE_FAIL)
    }
}