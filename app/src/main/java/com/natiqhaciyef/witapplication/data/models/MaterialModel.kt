package com.natiqhaciyef.witapplication.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MaterialModel(
    var id: String = "0",
    var image: String = "",
    var type: String,
    var title: String,
    var url: String,
    var downloadedUri: String? = null,
    var isDownloading: Boolean = false,
) : Parcelable