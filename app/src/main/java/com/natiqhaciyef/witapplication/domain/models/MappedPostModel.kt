package com.natiqhaciyef.witapplication.domain.models

import android.os.Parcelable
import com.natiqhaciyef.witapplication.data.models.UserModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MappedPostModel(
    var id: Int,
    var title: String,
    var description: String,
    var image: String?,
    var likeCount: Int,
    var location: String?,
    var publishDate: String,
    var user: UserModel,
) : Parcelable
