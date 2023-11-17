package com.natiqhaciyef.witapplication.domain_module_test.repository

import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.PostModel
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.InterviewQuestionResult
import com.natiqhaciyef.util.models.result.PostResult

class FakePostRepository(
    private val list: MutableList<PostModel>,
) : PostRepository {

    private val remotePostsResult = PostResult(list)
    private val localPostsList = list

    override suspend fun getAllPosts(): PostResult {
        return remotePostsResult
    }

    override suspend fun insertPost(postModel: PostModel): CRUDResponse {
        if (remotePostsResult.postResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val temp = remotePostsResult.postResult?.toMutableList()
        temp?.add(postModel)
        remotePostsResult.postResult = temp

        return if (remotePostsResult.postResult!!.contains(postModel))
            CRUDResponse(success = 1, message = BaseUseCase.INSERT_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.INSERT_FAIL)
    }

    override suspend fun updatePost(postModel: PostModel): CRUDResponse {
        if (remotePostsResult.postResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        if (!remotePostsResult.postResult!!.any { it.id == postModel.id })
            return CRUDResponse(success = 0, message = ErrorMessages.ELEMENT_NOT_FOUND)

        remotePostsResult.postResult!!.find { it.id == postModel.id }!!.apply {
            this.description = postModel.description
            this.image = postModel.image
            this.title = postModel.title
            this.likeCount = postModel.likeCount
            this.location = postModel.location
            this.publishDate = postModel.publishDate
            this.user = postModel.user
        }

        return if (remotePostsResult.postResult!!.contains(postModel))
            CRUDResponse(success = 1, message = BaseUseCase.UPDATE_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.UPDATE_FAIL)
    }

    override suspend fun removePost(id: Int): CRUDResponse {
        if (remotePostsResult.postResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val temp = remotePostsResult.postResult?.toMutableList()
        val element = temp!!.find { it.id == id }
        temp.removeAt(id)
        remotePostsResult.postResult = temp

        return if (!remotePostsResult.postResult!!.contains(element))
            CRUDResponse(success = 1, message = BaseUseCase.REMOVE_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.REMOVE_FAIL)
    }

    override suspend fun getAllSavedPosts(): List<PostModel>? {
        return localPostsList
    }

    override suspend fun savePost(postModel: PostModel) {
        localPostsList.add(postModel)
    }

    override suspend fun updateSavedPost(postModel: PostModel) {
        localPostsList.find { it.id == postModel.id }.apply {
            this?.description = postModel.description
            this?.image = postModel.image
            this?.title = postModel.title
            this?.likeCount = postModel.likeCount
            this?.location = postModel.location
            this?.publishDate = postModel.publishDate
            this?.user = postModel.user
        }
    }

    override suspend fun removeSavedPost(postModel: PostModel) {
        localPostsList.remove(postModel)
    }
}