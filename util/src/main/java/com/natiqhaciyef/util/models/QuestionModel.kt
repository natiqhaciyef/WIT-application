package com.natiqhaciyef.util.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.top.QuestionAbstraction
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("variants")
    var variants: List<String>,
    @SerializedName("correct_variant")
    var correctVariant: String,
    @SerializedName("topic")
    var topic: String,
    @SerializedName("point")
    var point: Double,
): Parcelable, QuestionAbstraction()
