package com.natiqhaciyef.witapplication.domain.repository

import com.natiqhaciyef.witapplication.data.models.VerifiedUserModel
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.VerifiedUserResult

interface VerifiedUserRepository {

    suspend fun getAllVerifiedUsers(): VerifiedUserResult

    suspend fun getVerifiedUserByEmail(email: String): VerifiedUserResult

    suspend fun insertVerifiedUser(verifiedUserModel: VerifiedUserModel): CRUDResponse

    suspend fun updateVerifiedUser(verifiedUserModel: VerifiedUserModel): CRUDResponse

    suspend fun removeVerifiedUser(id: Int): CRUDResponse


}