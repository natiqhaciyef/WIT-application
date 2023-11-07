package com.natiqhaciyef.witapplication.data.network.service

import com.natiqhaciyef.witapplication.BuildConfig
import com.natiqhaciyef.witapplication.data.network.VerifiedUserEndpoint
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.VerifiedUserResult
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VerifiedUserService {

    @GET(VerifiedUserEndpoint.GET)
    suspend fun getAllVerifiedUsers(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
    ): VerifiedUserResult

    @POST(VerifiedUserEndpoint.GET_BY_EMAIL)
    suspend fun getVerifiedUserByEmail(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("email") email: String,
    ): VerifiedUserResult

    @POST(VerifiedUserEndpoint.INSERT)
    suspend fun insertVerifiedUser(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("image") image: String,
        @Field("id_image") idImage: String,
        @Field("password") password: String,
        @Field("type") type: String,
    ): CRUDResponse

    @POST(VerifiedUserEndpoint.UPDATE)
    suspend fun updateVerifiedUser(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("image") image: String,
        @Field("id_image") idImage: String,
        @Field("password") password: String,
        @Field("type") type: String,
    ): CRUDResponse

    @POST(VerifiedUserEndpoint.DELETE)
    suspend fun removeVerifiedUser(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("id") id: Int,
    ): CRUDResponse
}