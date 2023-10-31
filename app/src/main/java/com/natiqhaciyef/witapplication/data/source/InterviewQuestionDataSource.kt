package com.natiqhaciyef.witapplication.data.source

import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.data.network.service.InterviewQuestionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InterviewQuestionDataSource(
    private val service: InterviewQuestionService,
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
}