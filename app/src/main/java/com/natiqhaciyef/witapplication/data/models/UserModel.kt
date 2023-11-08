package com.natiqhaciyef.witapplication.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.witapplication.data.models.enums.UserTypes
import com.natiqhaciyef.witapplication.data.models.top.UserAbstraction
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
) : Parcelable, UserAbstraction()

@Parcelize
data class UserWithoutPasswordModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
) : Parcelable

@Parcelize
data class VerifiedUserModel(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("phone")
    var phone: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("id_image")
    var idImage: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("type")
    var type: String = UserTypes.PreVerifiedUser.name,
) : UserAbstraction(), Parcelable
