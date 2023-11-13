package com.natiqhaciyef.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.ExamModel

data class ExamResult(
    @SerializedName("exam_table")
    val examResult: List<ExamModel>?
)