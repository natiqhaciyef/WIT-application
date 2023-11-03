package com.natiqhaciyef.witapplication.common.mappers

import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.models.UserWithoutPasswordModel


fun UserModel.mapToWithoutPassword() =
    UserWithoutPasswordModel(
        id = this.id,
        name = this.name,
        email = this.email
    )