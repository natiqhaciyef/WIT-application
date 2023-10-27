package com.natiqhaciyef.witapplication.data.models.top

import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel

open class QuestionAbstraction : CustomAbstraction<QuestionAbstraction> {

    override fun <T> getExactType(obj: T): String = when (obj) {
        is InterviewQuestionModel -> "Interview"
        is InterviewQuestionModel -> "Standard"
        else -> ""
    }
}