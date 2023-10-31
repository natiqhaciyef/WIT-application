package com.natiqhaciyef.witapplication.domain.repository.impl

import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.InterviewQuestionResult
import com.natiqhaciyef.witapplication.data.source.InterviewQuestionDataSource
import com.natiqhaciyef.witapplication.domain.repository.InterviewQuestionRepository

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