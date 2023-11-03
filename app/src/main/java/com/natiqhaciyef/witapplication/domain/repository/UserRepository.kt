package com.natiqhaciyef.witapplication.domain.repository

import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.UserResult

interface UserRepository {

    suspend fun getAllUsers(): UserResult

    suspend fun getUserByEmail(email: String): UserResult

    suspend fun insertUser(userModel: UserModel): CRUDResponse

    suspend fun updateUser(userModel: UserModel): CRUDResponse

    suspend fun removeUser(id: Int): CRUDResponse

}