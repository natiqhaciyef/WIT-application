package com.natiqhaciyef.data.source

import com.natiqhaciyef.data.local.dao.InterviewQuestionDao
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.data.network.service.InterviewQuestionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InterviewQuestionDataSource(
    private val service: InterviewQuestionService,
    private val dao: InterviewQuestionDao,
) {

    suspend fun getAllInterviewQuestions() = withContext(Dispatchers.IO) {
        service.getAllQuestions()
    }

    suspend fun insertInterviewQuestion(questionModel: InterviewQuestionModel) =
        withContext(Dispatchers.IO) {
            service.insertInterviewQuestion(
                title = questionModel.title,
                description = questionModel.description,
                solution = questionModel.solution,
                image = questionModel.image,
                level = questionModel.level,
                origin = questionModel.origin,
                field = questionModel.field,
                subfield = questionModel.subfield,
                date = questionModel.date
            )
        }

    suspend fun getAllSavedInterviewQuestions() = withContext(Dispatchers.IO) {
        dao.getAllSavedInterviewQuestions()
    }

    suspend fun saveInterviewQuestion(questionModel: InterviewQuestionModel) = withContext(Dispatchers.IO) {
        dao.saveInterviewQuestion(questionModel)
    }

    suspend fun removeSavedInterviewQuestion(questionModel: InterviewQuestionModel) =
        withContext(Dispatchers.IO) {
            dao.removeSavedInterviewQuestion(questionModel)
        }
}