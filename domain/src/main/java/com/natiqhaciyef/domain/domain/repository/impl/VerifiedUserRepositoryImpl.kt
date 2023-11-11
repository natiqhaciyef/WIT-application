package com.natiqhaciyef.domain.domain.repository.impl

import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.data.source.VerifiedUserDataSource
import com.natiqhaciyef.domain.domain.repository.VerifiedUserRepository

class VerifiedUserRepositoryImpl(
    private val ds: VerifiedUserDataSource,
) : VerifiedUserRepository {
    override suspend fun getAllVerifiedUsers() = ds.getAllVerifiedUsers()

    override suspend fun getVerifiedUserByEmail(email: String) = ds.getVerifiedUserByEmail(email)

    override suspend fun insertVerifiedUser(verifiedUserModel: VerifiedUserModel) =
        ds.insertVerifiedUser(verifiedUserModel)

    override suspend fun updateVerifiedUser(verifiedUserModel: VerifiedUserModel) =
        ds.updateVerifiedUser(verifiedUserModel)

    override suspend fun removeVerifiedUser(id: Int) = ds.removeVerifiedUser(id)
}