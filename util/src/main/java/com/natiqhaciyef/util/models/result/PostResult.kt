package com.natiqhaciyef.util.models.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.PostModel

data class PostResult(
    @SerializedName("post_table")
    var postResult: List<PostModel>?
)
