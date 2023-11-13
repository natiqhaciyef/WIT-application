package com.natiqhaciyef.data.network.service

import com.natiqhaciyef.data.BuildConfig
import com.natiqhaciyef.data.network.ExamEndpoint
import com.natiqhaciyef.data.network.result.ExamResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ExamService {

    @GET(ExamEndpoint.GET)
    suspend fun getAllExams(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
    ): ExamResult
}