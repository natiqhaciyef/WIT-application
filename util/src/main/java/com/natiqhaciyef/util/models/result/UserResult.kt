package com.natiqhaciyef.util.models.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.UserModel

data class UserResult(
    @SerializedName("user_table")
    var userResult: List<UserModel>?
)
