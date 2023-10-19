package com.natiqhaciyef.witapplication.data.network.service

import com.natiqhaciyef.witapplication.data.network.result.UserResult
import com.natiqhaciyef.witapplication.data.network.UserEndpoint
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query


interface UserService {
    @GET(UserEndpoint.GET)
    suspend fun getAllUsers(
        @Query("apiKey") key: String
    ): UserResult


    @GET(UserEndpoint.INSERT)
    suspend fun insertUser(
        @Query("apiKey") key: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): CRUDResponse


    @GET(UserEndpoint.UPDATE)
    suspend fun updateUser(
        @Query("apiKey") key: String,
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): CRUDResponse


    @GET(UserEndpoint.DELETE)
    suspend fun deleteUser(
        @Query("apiKey") key: String,
        @Field("id") id: String,
    ): CRUDResponse
}