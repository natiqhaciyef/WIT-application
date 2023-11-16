package com.natiqhaciyef.util.models.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.InterviewQuestionModel

data class InterviewQuestionResult(
    @SerializedName("interview_questions_table")
    var list: List<InterviewQuestionModel>?
)
