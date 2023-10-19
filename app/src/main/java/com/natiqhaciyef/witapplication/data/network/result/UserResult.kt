package com.natiqhaciyef.witapplication.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.witapplication.data.models.UserModel

data class UserResult(
    @SerializedName("user_table")
    val userResult: List<UserModel>?
)
