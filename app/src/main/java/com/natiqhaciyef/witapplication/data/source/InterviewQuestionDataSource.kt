package com.natiqhaciyef.witapplication.data.source

import com.natiqhaciyef.witapplication.data.network.service.InterviewQuestionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InterviewQuestionDataSource(
    private val service: InterviewQuestionService
) {

    suspend fun getAllInterviewQuestions() = withContext(Dispatchers.IO){
        service.getAllQuestions()
    }
}