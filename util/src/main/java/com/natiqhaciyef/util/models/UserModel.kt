package com.natiqhaciyef.util.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.enums.UserTypes
import com.natiqhaciyef.util.models.top.UserAbstraction
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var id: String = "0",
    var name: String,
    var email: String,
    var password: String,
) : Parcelable, UserAbstraction()

@Parcelize
data class UserWithoutPasswordModel(
    var id: String = "0",
    var name: String,
    var email: String,
) : Parcelable, UserAbstraction()

@Parcelize
data class VerifiedUserModel(
    var id: String = "0",
    var name: String,
    var email: String,
    var phone: String,
    var image: String,
    var idImage: String,
    var password: String,
    var type: String = UserTypes.PreVerifiedUser.name,
) : UserAbstraction(), Parcelable

@Parcelize
data class VerifiedUserWithoutPasswordModel(
    var id: String = "0",
    var name: String,
    var email: String,
    var phone: String,
    var image: String,
    var idImage: String,
    var type: String = UserTypes.PreVerifiedUser.name,
) : UserAbstraction(), Parcelable
