package com.natiqhaciyef.util.common.mappers

import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.util.models.VerifiedUserWithoutPasswordModel


fun UserModel.mapToWithoutPassword(): UserWithoutPasswordModel? {
    return if (this.name.isNotEmpty() && this.password.isNotEmpty() && this.email.isNotEmpty())
        UserWithoutPasswordModel(
            id = this.id,
            name = this.name,
            email = this.email
        )
    else
        null
}

fun VerifiedUserModel.mapToWithoutPassword(): VerifiedUserWithoutPasswordModel? {
    return if (this.name.isNotEmpty() && this.password.isNotEmpty() && this.email.isNotEmpty())
        VerifiedUserWithoutPasswordModel(
            id = this.id,
            name = this.name,
            email = this.email,
            phone = this.phone,
            image = this.image,
            idImage = this.idImage,
            type = this.type
        )
    else
        null
}
