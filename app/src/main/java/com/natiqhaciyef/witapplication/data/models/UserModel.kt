package com.natiqhaciyef.witapplication.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.costage.data.models.enums.UserTypes
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
): Parcelable
