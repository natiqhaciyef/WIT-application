package com.natiqhaciyef.util.common.mappers

import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel


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
