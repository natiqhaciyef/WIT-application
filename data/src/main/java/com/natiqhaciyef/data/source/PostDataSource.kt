package com.natiqhaciyef.data.source

import com.natiqhaciyef.data.local.dao.PostDao
import com.natiqhaciyef.util.models.PostModel
import com.natiqhaciyef.data.network.service.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostDataSource(
    private val service: PostService,
    private val dao: PostDao
) {

    suspend fun getAllPosts() = withContext(Dispatchers.IO){
        service.getAllPosts()
    }

    suspend fun insertPost(post: PostModel) = withContext(Dispatchers.IO){
        service.insertPost(
            title = post.title,
            description = post.description,
            image = post.image,
            likeCount = post.likeCount,
            location = post.location,
            publishDate = post.publishDate,
            user = post.user,
        )
    }

    suspend fun updatePost(post: PostModel) = withContext(Dispatchers.IO){
        service.updatePost(
            id = post.id,
            title = post.title,
            description = post.description,
            image = post.image,
            likeCount = post.likeCount,
            location = post.location,
            publishDate = post.publishDate,
            user = post.user,
        )
    }

    suspend fun removePost(id: Int) = withContext(Dispatchers.IO){
        service.deletePost(id = id)
    }


    suspend fun getAllSavedPosts() = withContext(Dispatchers.IO){
        dao.getAllSavedPosts()
    }

    suspend fun savePost(postModel: PostModel) = withContext(Dispatchers.IO){
        dao.savePost(postModel)
    }

    suspend fun updateSavedPost(postModel: PostModel) = withContext(Dispatchers.IO){
        dao.updateSavedPost(postModel)
    }

    suspend fun removeSavedPost(postModel: PostModel) = withContext(Dispatchers.IO){
        dao.removeSavedPost(postModel)
    }
}