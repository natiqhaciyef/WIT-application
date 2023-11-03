package com.natiqhaciyef.witapplication.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.witapplication.data.models.VerifiedUserModel

data class VerifiedUserResult(
    @SerializedName("verified_user_table")
    val verifiedUserResult: List<VerifiedUserModel>?,
)
