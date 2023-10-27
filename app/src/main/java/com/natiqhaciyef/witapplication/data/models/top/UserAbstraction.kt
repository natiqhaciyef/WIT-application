package com.natiqhaciyef.witapplication.data.models.top

import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.models.VerifiedUserModel

abstract class UserAbstraction : CustomAbstraction<UserAbstraction> {
    override fun <T> getExactType(obj: T): String = when (obj) {
        is VerifiedUserModel -> "Verified"
        is UserModel -> "Standard"
        else -> ""
    }

}