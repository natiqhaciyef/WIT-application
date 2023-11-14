package com.natiqhaciyef.util.models.mapped

import android.os.Parcelable
import com.natiqhaciyef.util.models.QuestionModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MappedExamModel(
    var id: Int = 0,
    var title: String,
    var field: String,
    var image: String,
    var questions: List<QuestionModel>,
    var level: String,
    var limitPoint: Double,
    var isActive: Boolean = false
): Parcelable
