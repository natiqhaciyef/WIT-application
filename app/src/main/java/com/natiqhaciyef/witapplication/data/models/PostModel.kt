package com.natiqhaciyef.witapplication.data.models

import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("image")
    var image: String?,
    @SerializedName("like_count")
    var likeCount: Double,
    @SerializedName("location")
    var location: String?,
    @SerializedName("publish_date")
    var publishDate: String,
    @SerializedName("user")
    var user: String
)
