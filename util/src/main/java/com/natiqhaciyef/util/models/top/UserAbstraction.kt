package com.natiqhaciyef.util.models.top

import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.VerifiedUserModel


abstract class UserAbstraction : CustomAbstraction<UserAbstraction> {
    override fun <T> getExactType(obj: T): String = when (obj) {
        is VerifiedUserModel -> "Verified"
        is UserModel -> "Standard"
        else -> ""
    }
}