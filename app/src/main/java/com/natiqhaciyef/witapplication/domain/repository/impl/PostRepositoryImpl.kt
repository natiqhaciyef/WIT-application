package com.natiqhaciyef.witapplication.domain.repository.impl

import com.natiqhaciyef.witapplication.data.models.PostModel
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.PostResult
import com.natiqhaciyef.witapplication.data.source.PostDataSource
import com.natiqhaciyef.witapplication.domain.repository.PostRepository

class PostRepositoryImpl(
    private val ds: PostDataSource
): PostRepository {
    override suspend fun getAllPosts(): PostResult = ds.getAllPosts()

    override suspend fun insertPost(postModel: PostModel): CRUDResponse = ds.insertPost(postModel)

    override suspend fun updatePost(postModel: PostModel): CRUDResponse = ds.updatePost(postModel)

    override suspend fun removePost(id: Int): CRUDResponse = ds.removePost(id)

    override suspend fun getAllSavedPosts(): List<PostModel>? = ds.getAllSavedPosts()

    override suspend fun savePost(postModel: PostModel) = ds.savePost(postModel)

    override suspend fun updateSavedPost(postModel: PostModel) = ds.updateSavedPost(postModel)

    override suspend fun removeSavedPost(postModel: PostModel) = ds.removeSavedPost(postModel)
}