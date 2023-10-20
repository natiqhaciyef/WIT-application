package com.natiqhaciyef.witapplication.domain.models

import com.natiqhaciyef.witapplication.data.models.UserModel

data class MappedPostModel(
    var id: Int,
    var title: String,
    var description: String,
    var image: String?,
    var likeCount: Int,
    var location: String?,
    var publishDate: String,
    var user: UserModel,
)
