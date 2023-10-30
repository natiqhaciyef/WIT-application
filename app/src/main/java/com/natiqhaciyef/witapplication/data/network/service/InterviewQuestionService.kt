package com.natiqhaciyef.witapplication.data.network.service

import com.natiqhaciyef.witapplication.BuildConfig
import com.natiqhaciyef.witapplication.data.network.InterviewQuestionEndpoint
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.InterviewQuestionResult
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface InterviewQuestionService {

    @GET(InterviewQuestionEndpoint.GET)
    suspend fun getAllQuestions(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
    ): InterviewQuestionResult

    @POST(InterviewQuestionEndpoint.INSERT)
    suspend fun insertInterviewQuestion(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("solution") solution: String,
        @Field("image") image: String?,
        @Field("level") level: String,
        @Field("origin") origin: String,
        @Field("field") field: String,
        @Field("date") date: String
    ): CRUDResponse

}