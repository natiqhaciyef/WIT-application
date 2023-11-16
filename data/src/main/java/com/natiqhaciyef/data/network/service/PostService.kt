package com.natiqhaciyef.data.network.service

import com.natiqhaciyef.data.BuildConfig
import com.natiqhaciyef.data.network.PostEndpoint
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.PostResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PostService {

    @GET(PostEndpoint.GET)
    suspend fun getAllPosts(
        @Query("apiKey") key: String = BuildConfig.API_KEY
    ): PostResult

    @POST(PostEndpoint.INSERT)
    @FormUrlEncoded
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
    @FormUrlEncoded
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
    @FormUrlEncoded
    suspend fun deletePost(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("id") id: Int,
    ): CRUDResponse
}