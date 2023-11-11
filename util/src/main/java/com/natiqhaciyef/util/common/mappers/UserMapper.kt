package com.natiqhaciyef.data.common.mappers

import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel


fun UserModel.mapToWithoutPassword() =
    UserWithoutPasswordModel(
        id = this.id,
        name = this.name,
        email = this.email
    )