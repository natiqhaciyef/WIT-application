package com.natiqhaciyef.voyagersaz.domain.model

import android.os.Parcelable
import com.natiqhaciyef.costage.data.models.enums.UserTypes
import kotlinx.parcelize.Parcelize

@Parcelize
data class MappedUser(
    var id: Int = 0,
    var name: String,
    var email: String,
    var password: String,
    var type: UserTypes = UserTypes.User
) : Parcelable
