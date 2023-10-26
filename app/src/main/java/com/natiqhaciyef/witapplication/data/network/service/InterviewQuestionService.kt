package com.natiqhaciyef.witapplication.data.network.service

import com.natiqhaciyef.witapplication.data.network.InterviewQuestionEndpoint
import com.natiqhaciyef.witapplication.data.network.result.InterviewQuestionResult
import retrofit2.http.GET

interface InterviewQuestionService {

    @GET(InterviewQuestionEndpoint.GET)
    suspend fun getAllQuestions(): InterviewQuestionResult

}