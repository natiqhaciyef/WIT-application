package com.natiqhaciyef.data.source

import com.natiqhaciyef.data.network.service.ExamService
import com.natiqhaciyef.util.models.ExamModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExamDataSource(val service: ExamService) {

    suspend fun getAllExams() = withContext(Dispatchers.IO) {
        service.getAllExams()
    }

    suspend fun insertExam(examModel: ExamModel) = withContext(Dispatchers.IO) {
        service.insertExam(
            title = examModel.title,
            field = examModel.field,
            image = examModel.image,
            questions = examModel.questions,
            level = examModel.level,
            limitPoint = examModel.limitPoint,
            isActive = examModel.isActive
        )
    }

}