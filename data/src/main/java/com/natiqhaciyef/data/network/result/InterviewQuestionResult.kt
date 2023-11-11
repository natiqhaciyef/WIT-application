package com.natiqhaciyef.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.InterviewQuestionModel

data class InterviewQuestionResult(
    @SerializedName("interview_questions_table")
    val list: List<InterviewQuestionModel>?
)
