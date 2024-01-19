package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.domain.domain.base.BaseRepository
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.InterviewQuestionResult

interface InterviewQuestionRepository : BaseRepository{

    suspend fun getAllInterviewQuestion(): InterviewQuestionResult

    suspend fun insertInterviewQuestion(questionModel: InterviewQuestionModel): CRUDResponse

    suspend fun getAllSavedInterviewQuestions(): List<InterviewQuestionModel>?

    suspend fun saveInterviewQuestion(questionModel: InterviewQuestionModel)

    suspend fun removeSavedInterviewQuestion(questionModel: InterviewQuestionModel)
}