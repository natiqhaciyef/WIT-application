package com.natiqhaciyef.data.network.service

import com.natiqhaciyef.data.BuildConfig
import com.natiqhaciyef.data.network.UserEndpoint
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.UserResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface UserService {
    @GET(UserEndpoint.GET)
    suspend fun getAllUsers(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
    ): UserResult

    @POST(UserEndpoint.GET_BY_EMAIL)
    @FormUrlEncoded
    suspend fun getUserByEmail(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("email") email: String,
    ): UserResult

    @POST(UserEndpoint.INSERT)
    @FormUrlEncoded
    suspend fun insertUser(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): CRUDResponse


    @POST(UserEndpoint.UPDATE)
    @FormUrlEncoded
    suspend fun updateUser(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): CRUDResponse


    @POST(UserEndpoint.DELETE)
    @FormUrlEncoded
    suspend fun deleteUser(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("id") id: Int,
    ): CRUDResponse
}