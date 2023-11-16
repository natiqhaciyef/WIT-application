package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.VerifiedUserResult
import com.natiqhaciyef.util.models.VerifiedUserModel

interface VerifiedUserRepository {

    suspend fun getAllVerifiedUsers(): VerifiedUserResult

    suspend fun getVerifiedUserByEmail(email: String): VerifiedUserResult

    suspend fun insertVerifiedUser(verifiedUserModel: VerifiedUserModel): CRUDResponse

    suspend fun updateVerifiedUser(verifiedUserModel: VerifiedUserModel): CRUDResponse

    suspend fun removeVerifiedUser(id: Int): CRUDResponse


}