package com.natiqhaciyef.witapplication.domain.repository

import com.natiqhaciyef.witapplication.data.models.PostModel
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.PostResult

interface PostRepository {

    suspend fun getAllPosts(): PostResult

    suspend fun insertPost(postModel: PostModel): CRUDResponse

    suspend fun updatePost(postModel: PostModel): CRUDResponse

    suspend fun removePost(id: Int): CRUDResponse

    suspend fun getAllSavedPosts(): List<PostModel>?

    suspend fun savePostLocal(postModel: PostModel)

    suspend fun removePostLocal(postModel: PostModel)

}