package com.natiqhaciyef.util.models.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.VerifiedUserModel

data class VerifiedUserResult(
    @SerializedName("verified_user_table")
    var verifiedUserResult: List<VerifiedUserModel>?,
)
