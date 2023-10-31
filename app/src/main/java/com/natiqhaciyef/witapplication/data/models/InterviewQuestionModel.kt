package com.natiqhaciyef.witapplication.data.models

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.witapplication.data.models.top.QuestionAbstraction
import kotlinx.parcelize.Parcelize

@Entity("interview_question_table")
@Parcelize
data class InterviewQuestionModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("solution")
    val solution: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("level")
    val level: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("field")
    val field: String,
    @SerializedName("subfield")
    val subfield: String,
    @SerializedName("date")
    val date: String
): Parcelable, QuestionAbstraction()
