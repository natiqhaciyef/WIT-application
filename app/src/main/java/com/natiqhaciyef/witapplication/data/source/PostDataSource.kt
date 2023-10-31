package com.natiqhaciyef.witapplication.data.source

import com.natiqhaciyef.witapplication.data.local.dao.PostDao
import com.natiqhaciyef.witapplication.data.models.PostModel
import com.natiqhaciyef.witapplication.data.network.service.PostService
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

    suspend fun savePostLocal(postModel: PostModel) = withContext(Dispatchers.IO){
        dao.savePost(postModel)
    }

    suspend fun removePostLocal(postModel: PostModel) = withContext(Dispatchers.IO){
        dao.removePost(postModel)
    }
}