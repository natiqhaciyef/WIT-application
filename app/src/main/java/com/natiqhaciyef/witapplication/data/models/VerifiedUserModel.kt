package com.natiqhaciyef.witapplication.data.models

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.costage.data.models.enums.UserTypes
import com.natiqhaciyef.witapplication.data.models.top.UserAbstraction

data class VerifiedUserModel(
    @SerializedName("id")
    var id: Int,
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
    var type: String = UserTypes.VerifiedUser.name
): UserAbstraction()
