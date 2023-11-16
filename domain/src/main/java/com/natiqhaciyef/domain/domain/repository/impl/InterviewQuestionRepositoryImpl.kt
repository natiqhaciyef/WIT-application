package com.natiqhaciyef.domain.domain.repository.impl

import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.InterviewQuestionResult
import com.natiqhaciyef.data.source.InterviewQuestionDataSource
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository

class InterviewQuestionRepositoryImpl(
    private val ds: InterviewQuestionDataSource,
) : InterviewQuestionRepository {
    override suspend fun getAllInterviewQuestion(): InterviewQuestionResult =
        ds.getAllInterviewQuestions()

    override suspend fun insertInterviewQuestion(questionModel: InterviewQuestionModel): CRUDResponse =
        ds.insertInterviewQuestion(questionModel)

    override suspend fun getAllSavedInterviewQuestions(): List<InterviewQuestionModel>? =
        ds.getAllSavedInterviewQuestions()

    override suspend fun saveInterviewQuestion(questionModel: InterviewQuestionModel) =
        ds.saveInterviewQuestion(questionModel)

    override suspend fun removeSavedInterviewQuestion(questionModel: InterviewQuestionModel) =
        ds.removeSavedInterviewQuestion(questionModel)
}