package com.natiqhaciyef.util.common.mappers

import com.google.gson.Gson
import com.natiqhaciyef.util.models.PostModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.util.models.mapped.MappedPostModel

fun PostModel.toMappedPost(): MappedPostModel? {

    return if (
        this.user.isNotEmpty() &&
        this.title.isNotEmpty() &&
        this.description.isNotEmpty() &&
        this.publishDate.isNotEmpty()
    )
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
    else
        null

}

fun MappedPostModel.toPost(): PostModel? {
    return if (
        this.title.isNotEmpty() &&
        this.description.isNotEmpty() &&
        this.publishDate.isNotEmpty()
    ) {
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
    } else
        null
}
