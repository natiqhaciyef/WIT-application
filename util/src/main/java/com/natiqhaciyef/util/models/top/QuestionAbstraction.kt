package com.natiqhaciyef.util.models.top

import com.natiqhaciyef.util.models.InterviewQuestionModel

open class QuestionAbstraction : CustomAbstraction<QuestionAbstraction> {

    override fun <T> getExactType(obj: T): String = when (obj) {
        is InterviewQuestionModel -> "Interview"
        is InterviewQuestionModel -> "Standard"
        else -> ""
    }
}