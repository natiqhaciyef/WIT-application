package com.natiqhaciyef.util.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Entity("exam_table")
@Parcelize
data class ExamModel(
    @PrimaryKey(autoGenerate = false)
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
    @SerializedName("is_active")
    var isActive: String
): Parcelable
