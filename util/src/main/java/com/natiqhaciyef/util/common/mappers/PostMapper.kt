package com.natiqhaciyef.util.common.mappers

import com.google.gson.Gson
import com.natiqhaciyef.util.models.PostModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.util.models.mapped.MappedPostModel

fun PostModel.toMappedPost() =
    MappedPostModel(
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.image,
        likeCount = this.likeCount,
        location = this.location,
        publishDate = this.publishDate,
        user = Gson().fromJson(this.user, UserWithoutPasswordModel::class.java)
    )

fun MappedPostModel.toPost() =
    PostModel(
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.image,
        likeCount = this.likeCount,
        location = this.location,
        publishDate = this.publishDate,
        user = Gson().toJson(this.user)
    )