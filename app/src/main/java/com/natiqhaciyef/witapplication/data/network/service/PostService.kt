package com.natiqhaciyef.witapplication.data.network.service

import com.natiqhaciyef.witapplication.BuildConfig
import com.natiqhaciyef.witapplication.data.network.PostEndpoint
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.PostResult
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostService {

    @GET(PostEndpoint.GET)
    suspend fun getAllPosts(
        @Query("apiKey") key: String = BuildConfig.API_KEY
    ): PostResult

    @POST(PostEndpoint.INSERT)
    suspend fun insertPost(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("image") image: String?,
        @Field("like_count") likeCount: Int,
        @Field("location") location: String?,
        @Field("publish_date") publishDate: String,
        @Field("user") user: String,
    ): CRUDResponse

    @POST(PostEndpoint.UPDATE)
    suspend fun updatePost(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("image") image: String?,
        @Field("like_count") likeCount: Int,
        @Field("location") location: String?,
        @Field("publish_date") publishDate: String,
        @Field("user") user: String,
    ): CRUDResponse

    @POST(PostEndpoint.DELETE)
    suspend fun deletePost(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("id") id: Int,
    ): CRUDResponse
}