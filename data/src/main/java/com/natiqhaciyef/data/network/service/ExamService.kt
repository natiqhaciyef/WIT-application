package com.natiqhaciyef.data.network.service

import com.natiqhaciyef.data.BuildConfig
import com.natiqhaciyef.data.network.ExamEndpoint
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ExamResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ExamService {

    @GET(ExamEndpoint.GET)
    suspend fun getAllExams(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
    ): ExamResult

    @POST(ExamEndpoint.INSERT)
    @FormUrlEncoded
    suspend fun insertExam(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("title") title: String,
        @Field("field") field: String,
        @Field("image") image: String,
        @Field("questions") questions: String,
        @Field("level") level: String,
        @Field("limit_point") limitPoint: Double,
        @Field("is_active") isActive: String
    ): CRUDResponse
}