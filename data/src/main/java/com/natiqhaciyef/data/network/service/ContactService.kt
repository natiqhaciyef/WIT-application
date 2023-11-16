package com.natiqhaciyef.data.network.service


import com.natiqhaciyef.data.BuildConfig
import com.natiqhaciyef.data.network.ContactEndpoint
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ContactResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ContactService {
    @GET(ContactEndpoint.GET)
    suspend fun getAllContacts(
        @Query("apiKey") key: String = BuildConfig.API_KEY
    ): ContactResult

    @POST(ContactEndpoint.INSERT)
    @FormUrlEncoded
    suspend fun insertContact(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("description") description: String,
        @Field("field") field: String,
        @Field("date") date: String,
        @Field("is_checked") isChecked: Boolean,
    ): CRUDResponse

    @POST(ContactEndpoint.DELETE)
    @FormUrlEncoded
    suspend fun removeContact(
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Field("id") id: Int,
    ): CRUDResponse

}