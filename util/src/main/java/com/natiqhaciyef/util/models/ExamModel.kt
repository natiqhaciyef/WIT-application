package com.natiqhaciyef.util.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExamModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("field")
    var field: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("questions")
    var questions: String,
    @SerializedName("level")
    var level: String,
    @SerializedName("limit_point")
    var limitPoint: Double,
    @SerializedName("is_passed")
    var isPassed: Boolean = false
): Parcelable
