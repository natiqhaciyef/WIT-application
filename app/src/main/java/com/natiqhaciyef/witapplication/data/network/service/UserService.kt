package com.natiqhaciyef.witapplication.data.network.service

import com.natiqhaciyef.witapplication.BuildConfig
import com.natiqhaciyef.witapplication.data.network.result.UserResult
import com.natiqhaciyef.witapplication.data.network.UserEndpoint
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface UserService {
    @GET(UserEndpoint.GET)
    suspend fun getAllUsers(
        @Query("apiKey") key: String = BuildConfig.API_KEY
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