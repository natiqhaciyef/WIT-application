package com.natiqhaciyef.util.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("post_table")
data class PostModel(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("image")
    var image: String?,
    @SerializedName("like_count")
    var likeCount: Int,
    @SerializedName("location")
    var location: String?,
    @SerializedName("publish_date")
    var publishDate: String,
    @SerializedName("user")
    var user: String
)
