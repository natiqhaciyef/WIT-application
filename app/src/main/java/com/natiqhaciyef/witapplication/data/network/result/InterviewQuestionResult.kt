package com.natiqhaciyef.witapplication.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel

data class InterviewQuestionResult(
    @SerializedName("interview_questions_table")
    val list: List<InterviewQuestionModel>?
)
