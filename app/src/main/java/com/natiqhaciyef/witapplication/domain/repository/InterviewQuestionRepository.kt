package com.natiqhaciyef.witapplication.domain.repository

import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.InterviewQuestionResult

interface InterviewQuestionRepository {

    suspend fun getAllInterviewQuestion(): InterviewQuestionResult

    suspend fun insertInterviewQuestion(questionModel: InterviewQuestionModel): CRUDResponse

    suspend fun getAllSavedInterviewQuestions(): List<InterviewQuestionModel>?

    suspend fun saveInterviewQuestion(questionModel: InterviewQuestionModel)

    suspend fun removeSavedInterviewQuestion(questionModel: InterviewQuestionModel)
}