package com.natiqhaciyef.data.source

import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.data.network.service.VerifiedUserService

class VerifiedUserDataSource(
    private val service: VerifiedUserService,
) {

    suspend fun getAllVerifiedUsers() = service.getAllVerifiedUsers()

    suspend fun getVerifiedUserByEmail(email: String) =
        service.getVerifiedUserByEmail(email = email)

    suspend fun insertVerifiedUser(verifiedUserModel: VerifiedUserModel) =
        service.insertVerifiedUser(
            name = verifiedUserModel.name,
            email = verifiedUserModel.email,
            phone = verifiedUserModel.phone,
            image = verifiedUserModel.image,
            idImage = verifiedUserModel.idImage,
            password = verifiedUserModel.password,
            type = verifiedUserModel.type,
        )

    suspend fun updateVerifiedUser(verifiedUserModel: VerifiedUserModel) =
        service.updateVerifiedUser(
            id = verifiedUserModel.id,
            name = verifiedUserModel.name,
            email = verifiedUserModel.email,
            phone = verifiedUserModel.phone,
            image = verifiedUserModel.image,
            idImage = verifiedUserModel.idImage,
            password = verifiedUserModel.password,
            type = verifiedUserModel.type,
        )

    suspend fun removeVerifiedUser(id: Int) = service.removeVerifiedUser(id = id)


}