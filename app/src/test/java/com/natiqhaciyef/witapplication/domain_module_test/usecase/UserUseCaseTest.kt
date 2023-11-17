package com.natiqhaciyef.witapplication.domain_module_test.usecase

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.user.GetAllUserRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.user.GetUserByEmailUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.user.InsertUserRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.user.RemoveUserRemoteUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.user.UpdateUserRemoteUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.domain_module_test.repository.FakeUserRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserUseCaseTest {
    private val fakeUserRepository =
        FakeUserRepository(
            mutableListOf(
                DefaultImpl.user,
                DefaultImpl.user.copy(id = 1),
                DefaultImpl.user.copy(id = 2, email = "nathan123@gmail.com"),
            )
        )

    @Test
    fun `get all user use case (remote) returns success`() = runTest {
        val result = GetAllUserRemoteUseCase(fakeUserRepository).invoke().last()

        assertThat(result).isEqualTo(
            Resource.success(
                mutableListOf(
                    DefaultImpl.user,
                    DefaultImpl.user.copy(id = 1),
                    DefaultImpl.user.copy(id = 2, email = "nathan123@gmail.com"),
                )
            )
        )
    }

    @Test
    fun `get user by email use case (remote) returns success`() = runTest {
        val result = GetUserByEmailUseCase(fakeUserRepository).invoke("nathan123@gmail.com").last()
        assertThat(result).isEqualTo(
            Resource.success(
                mutableListOf(DefaultImpl.user.copy(id = 2, email = "nathan123@gmail.com"))
            )
        )
    }

    @Test
    fun `insert user use case (remote) returns success`() = runTest {
        val result =
            InsertUserRemoteUseCase(fakeUserRepository).invoke(DefaultImpl.user.copy(id = 3)).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

    @Test
    fun `remove user use case (remote) returns success`() = runTest {
        val result = RemoveUserRemoteUseCase(fakeUserRepository).invoke(2).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }

    @Test
    fun `update user use case (remote) returns success`() = runTest {
        val result = UpdateUserRemoteUseCase(fakeUserRepository).invoke(
            DefaultImpl.user.copy(id = 2, email = "nathanAb@gmail.com")
        ).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.UPDATE_SUCCESS))
    }


    @Test
    fun `insert user use case (remote) with user model contains empty field returns error`() =
        runTest {
            val result =
                InsertUserRemoteUseCase(fakeUserRepository)
                    .invoke(DefaultImpl.user.copy(id = 1, name = "")).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }

    @Test
    fun `insert user use case (remote) with user model contains wrong email returns error`() =
        runTest {
            val result =
                InsertUserRemoteUseCase(fakeUserRepository)
                    .invoke(DefaultImpl.user.copy(id = 1, email = "wrongmail@yahoo.com")).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }

    @Test
    fun `insert user use case (remote) with user model contains wrong password returns error`() =
        runTest {
            val result =
                InsertUserRemoteUseCase(fakeUserRepository)
                    .invoke(DefaultImpl.user.copy(id = 1, password = "hello")).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }

    @Test
    fun `update user use case (remote) with user model contains empty field returns error`() =
        runTest {
            val result =
                UpdateUserRemoteUseCase(fakeUserRepository)
                    .invoke(DefaultImpl.user.copy(id = 1, name = "")).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }

    @Test
    fun `update user use case (remote) with user model contains wrong email returns error`() =
        runTest {
            val result =
                UpdateUserRemoteUseCase(fakeUserRepository)
                    .invoke(DefaultImpl.user.copy(id = 1, email = "wrongmail@yahoo.com")).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }

    @Test
    fun `update user use case (remote) with user model contains wrong password returns error`() =
        runTest {
            val result =
                UpdateUserRemoteUseCase(fakeUserRepository)
                    .invoke(DefaultImpl.user.copy(id = 1, password = "hello")).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
        }
}