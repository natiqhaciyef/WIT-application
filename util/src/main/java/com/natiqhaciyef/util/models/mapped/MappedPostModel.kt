package com.natiqhaciyef.util.models.mapped

import android.os.Parcelable
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
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
    var user: UserWithoutPasswordModel,
) : Parcelable
