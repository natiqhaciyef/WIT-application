package com.natiqhaciyef.domain.domain.repository.impl

import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.data.network.result.CRUDResponse
import com.natiqhaciyef.data.network.result.UserResult
import com.natiqhaciyef.data.source.UserDataSource
import com.natiqhaciyef.domain.domain.repository.UserRepository

class UserRepositoryImpl(
    private val ds: UserDataSource
): UserRepository {
    override suspend fun getAllUsers(): UserResult = ds.getAllUsers()

    override suspend fun getUserByEmail(email: String): UserResult = ds.getUserByEmail(email)

    override suspend fun insertUser(userModel: UserModel): CRUDResponse = ds.insertUser(userModel)

    override suspend fun updateUser(userModel: UserModel): CRUDResponse = ds.updateUser(userModel)

    override suspend fun removeUser(id: Int): CRUDResponse = ds.removeUser(id)
}