package com.natiqhaciyef.util.models.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.ExamModel

data class ExamResult(
    @SerializedName("exam_table")
    var examResult: List<ExamModel>?
)