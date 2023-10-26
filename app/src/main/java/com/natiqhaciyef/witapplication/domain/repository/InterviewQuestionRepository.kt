package com.natiqhaciyef.witapplication.domain.repository

import com.natiqhaciyef.witapplication.data.network.result.InterviewQuestionResult

interface InterviewQuestionRepository {

    suspend fun getAllInterviewQuestion(): InterviewQuestionResult
}