package com.natiqhaciyef.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.PostModel

data class PostResult(
    @SerializedName("post_table")
    val postResult: List<PostModel>?
)
