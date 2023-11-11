package com.natiqhaciyef.util.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.top.QuestionAbstraction
import kotlinx.parcelize.Parcelize

@Entity("interview_question_table")
@Parcelize
data class InterviewQuestionModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("solution")
    var solution: String,
    @SerializedName("image")
    var image: String? = "",
    @SerializedName("level")
    var level: String,
    @SerializedName("origin")
    var origin: String,
    @SerializedName("field")
    var field: String,
    @SerializedName("subfield")
    var subfield: String,
    @SerializedName("date")
    var date: String
): Parcelable, QuestionAbstraction()
