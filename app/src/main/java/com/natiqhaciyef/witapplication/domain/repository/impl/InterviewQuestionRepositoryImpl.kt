package com.natiqhaciyef.witapplication.domain.repository.impl

import com.natiqhaciyef.witapplication.data.network.result.InterviewQuestionResult
import com.natiqhaciyef.witapplication.data.source.InterviewQuestionDataSource
import com.natiqhaciyef.witapplication.domain.repository.InterviewQuestionRepository

class InterviewQuestionRepositoryImpl(
    private val ds: InterviewQuestionDataSource
) : InterviewQuestionRepository {
    override suspend fun getAllInterviewQuestion(): InterviewQuestionResult =
        ds.getAllInterviewQuestions()
}