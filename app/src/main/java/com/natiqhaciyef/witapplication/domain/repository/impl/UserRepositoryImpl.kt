package com.natiqhaciyef.witapplication.domain.repository.impl

import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.UserResult
import com.natiqhaciyef.witapplication.data.source.UserDataSource
import com.natiqhaciyef.witapplication.domain.repository.UserRepository

class UserRepositoryImpl(
    private val ds: UserDataSource
): UserRepository {
    override suspend fun getAllUsers(): UserResult = ds.getAllUsers()

    override suspend fun insertUser(userModel: UserModel): CRUDResponse = ds.insertUser(userModel)

    override suspend fun updateUser(userModel: UserModel): CRUDResponse = ds.updateUser(userModel)

    override suspend fun removeUser(id: Int): CRUDResponse = ds.removeUser(id)
}