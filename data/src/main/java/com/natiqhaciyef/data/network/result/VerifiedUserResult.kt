package com.natiqhaciyef.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.VerifiedUserModel

data class VerifiedUserResult(
    @SerializedName("verified_user_table")
    val verifiedUserResult: List<VerifiedUserModel>?,
)
