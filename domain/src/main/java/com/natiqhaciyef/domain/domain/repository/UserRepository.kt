package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.UserResult
import com.natiqhaciyef.util.models.UserModel

interface UserRepository {

    suspend fun getAllUsers(): UserResult

    suspend fun getUserByEmail(email: String): UserResult

    suspend fun insertUser(userModel: UserModel): CRUDResponse

    suspend fun updateUser(userModel: UserModel): CRUDResponse

    suspend fun removeUser(id: Int): CRUDResponse

}