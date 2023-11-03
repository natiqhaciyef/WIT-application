package com.natiqhaciyef.witapplication.data.source

import com.natiqhaciyef.witapplication.BuildConfig
import com.natiqhaciyef.witapplication.data.models.VerifiedUserModel
import com.natiqhaciyef.witapplication.data.network.VerifiedUserEndpoint
import com.natiqhaciyef.witapplication.data.network.result.VerifiedUserResult
import com.natiqhaciyef.witapplication.data.network.service.VerifiedUserService
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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