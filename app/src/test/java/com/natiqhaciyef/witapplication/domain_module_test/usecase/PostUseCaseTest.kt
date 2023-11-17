package com.natiqhaciyef.witapplication.domain_module_test.usecase

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.GetAllSavedPostsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.RemoveSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.SavePostUseCase
import com.natiqhaciyef.domain.domain.usecase.local.post.UpdateSavedPostUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.GetAllPostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.InsertPostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.RemovePostRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.post.UpdatePostRemoteUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.mappers.toPost
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.util.models.result.PostResult
import com.natiqhaciyef.witapplication.domain_module_test.repository.FakePostRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PostUseCaseTest {
    private val fakePostRepository = FakePostRepository(
        mutableListOf(
            DefaultImpl.mappedPost.toPost()!!,
            DefaultImpl.mappedPost.copy(id = 1).toPost()!!,
            DefaultImpl.mappedPost.copy(id = 2).toPost()!!
        )
    )

    @Test
    fun `get all posts use case (remote) returns success`() = runTest {
        val result = GetAllPostRemoteUseCase(fakePostRepository).invoke().last()

        assertThat(result).isEqualTo(
            Resource.success(
                mutableListOf(
                    DefaultImpl.mappedPost,
                    DefaultImpl.mappedPost.copy(id = 1),
                    DefaultImpl.mappedPost.copy(id = 2)
                )
            )
        )
    }

    @Test
    fun `insert post use case (remote) returns success`() = runTest {
        val result =
            InsertPostRemoteUseCase(fakePostRepository).invoke(DefaultImpl.mappedPost.copy(id = 3))
                .last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

    @Test
    fun `remove post use case (remote) returns success`() = runTest {
        val result = RemovePostRemoteUseCase(fakePostRepository).invoke(1).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }

    @Test
    fun `update post use case (remote) returns success`() = runTest {
        val result = UpdatePostRemoteUseCase(fakePostRepository)
            .invoke(DefaultImpl.mappedPost.copy(id = 1, title = "Hello world!")).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.UPDATE_SUCCESS))
    }

    @Test
    fun `get all saved posts use case (local) returns success`() = runTest {
        val result = GetAllSavedPostsUseCase(fakePostRepository)
            .invoke().last()

        assertThat(result).isEqualTo(
            Resource.success(
                listOf(
                    DefaultImpl.mappedPost,
                    DefaultImpl.mappedPost.copy(id = 1),
                    DefaultImpl.mappedPost.copy(id = 2)
                )
            )
        )
    }

    @Test
    fun `save post use case (local) returns success`() = runTest {
        val result = SavePostUseCase(fakePostRepository)
            .invoke(DefaultImpl.mappedPost.copy(id = 3)).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

    @Test
    fun `remove saved post use case (local) returns success`() = runTest {
        val result = RemoveSavedPostUseCase(fakePostRepository)
            .invoke(DefaultImpl.mappedPost.copy(id = 3)).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }

    @Test
    fun `update saved post use case (local) returns success`() = runTest {
        val result = UpdateSavedPostUseCase(fakePostRepository)
            .invoke(DefaultImpl.mappedPost.copy(id = 0, title = "Hello world")).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.UPDATE_SUCCESS))
    }


    @Test
    fun `insert post use case (remote) with post model contains empty field returns error`() = runTest {
        val result =
            InsertPostRemoteUseCase(fakePostRepository)
                .invoke(DefaultImpl.mappedPost.copy(id = 3, title = ""))
                .last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
    }

    @Test
    fun `update post use case (remote) with post model contains empty field returns error`() = runTest {
        val result = UpdatePostRemoteUseCase(fakePostRepository)
            .invoke(DefaultImpl.mappedPost
                .copy(id = 1, user = UserWithoutPasswordModel(email = "", id = 0, name = "Empty"))).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
    }

    @Test
    fun `save post use case (local) with post model contains empty field returns error`() = runTest {
        val result = SavePostUseCase(fakePostRepository)
            .invoke(DefaultImpl.mappedPost.copy(id = 3, publishDate = "")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
    }

    @Test
    fun `remove saved post use case (local) with post model contains empty field returns error`() = runTest {
        val result = RemoveSavedPostUseCase(fakePostRepository)
            .invoke(DefaultImpl.mappedPost.copy(id = 3, publishDate = "")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
    }

    @Test
    fun `update saved post use case (local) with post model contains empty field returns error`() = runTest {
        val result = UpdateSavedPostUseCase(fakePostRepository)
            .invoke(DefaultImpl.mappedPost.copy(id = 0, title = "")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
    }

}