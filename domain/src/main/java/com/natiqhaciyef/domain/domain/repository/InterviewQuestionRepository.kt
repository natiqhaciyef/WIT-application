package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.data.network.result.CRUDResponse
import com.natiqhaciyef.data.network.result.InterviewQuestionResult

interface InterviewQuestionRepository {

    suspend fun getAllInterviewQuestion(): InterviewQuestionResult

    suspend fun insertInterviewQuestion(questionModel: InterviewQuestionModel): CRUDResponse

    suspend fun getAllSavedInterviewQuestions(): List<InterviewQuestionModel>?

    suspend fun saveInterviewQuestion(questionModel: InterviewQuestionModel)

    suspend fun removeSavedInterviewQuestion(questionModel: InterviewQuestionModel)
}