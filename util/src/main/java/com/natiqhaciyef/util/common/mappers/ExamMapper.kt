package com.natiqhaciyef.util.common.mappers

import com.google.gson.Gson
import com.natiqhaciyef.util.common.util.helpers.toSQLiteList
import com.natiqhaciyef.util.common.util.helpers.toSQLiteString
import com.natiqhaciyef.util.models.ExamModel
import com.natiqhaciyef.util.models.QuestionModel
import com.natiqhaciyef.util.models.mapped.MappedExamModel


fun ExamModel.toMappedExam(): MappedExamModel? {
    val questions = mutableListOf<QuestionModel>()
    val listConvert = this.questions.toSQLiteList()
    for (json in listConvert) {
        questions.add(Gson().fromJson(json, QuestionModel::class.java))
    }

    return if (
        questions.isNotEmpty() &&
        (this.isActive == "true" || this.isActive == "false") &&
        this.title.isNotEmpty() &&
        this.field.isNotEmpty() &&
        this.level.isNotEmpty()
    )
        MappedExamModel(
            id = this.id,
            title = this.title,
            field = this.field,
            questions = questions,
            level = this.level,
            image = this.image,
            limitPoint = this.limitPoint,
            isActive = this.isActive.toBoolean()
        )
    else
        null
}

fun MappedExamModel.toExam(): ExamModel? {
    val strs = mutableListOf<String>()
    for (str in this.questions) {
        strs.add(Gson().toJson(str))
    }


    return if (
        strs.isNotEmpty() &&
        this.title.isNotEmpty() &&
        this.field.isNotEmpty() &&
        this.level.isNotEmpty()
    )
        ExamModel(
            id = this.id,
            title = this.title,
            field = this.field,
            questions = strs.toSQLiteString(),
            level = this.level,
            image = this.image,
            limitPoint = this.limitPoint,
            isActive = this.isActive.toString()
        )
    else
        null
}