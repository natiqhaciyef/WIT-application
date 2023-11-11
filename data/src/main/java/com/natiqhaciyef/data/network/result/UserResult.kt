package com.natiqhaciyef.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.UserModel

data class UserResult(
    @SerializedName("user_table")
    val userResult: List<UserModel>?
)
