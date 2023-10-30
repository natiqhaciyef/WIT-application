package com.natiqhaciyef.witapplication.common.mappers

import com.google.gson.Gson
import com.natiqhaciyef.witapplication.data.models.PostModel
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.data.models.UserWithoutPasswordModel
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel

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