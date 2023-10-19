package com.natiqhaciyef.witapplication.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.voyagersaz.data.model.service.PostModel

data class PostResult(
    @SerializedName("post_table")
    val postResult: List<PostModel>?
)
