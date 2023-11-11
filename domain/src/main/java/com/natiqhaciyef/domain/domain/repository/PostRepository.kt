package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.data.network.result.CRUDResponse
import com.natiqhaciyef.data.network.result.PostResult
import com.natiqhaciyef.util.models.PostModel

interface PostRepository {

    suspend fun getAllPosts(): PostResult

    suspend fun insertPost(postModel: PostModel): CRUDResponse

    suspend fun updatePost(postModel: PostModel): CRUDResponse

    suspend fun removePost(id: Int): CRUDResponse

    suspend fun getAllSavedPosts(): List<PostModel>?

    suspend fun savePost(postModel: PostModel)

    suspend fun updateSavedPost(postModel: PostModel)

    suspend fun removeSavedPost(postModel: PostModel)

}