package com.natiqhaciyef.witapplication.domain_module_test.usecase

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.verified_user.GetAllVerifiedUsersUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.verified_user.GetVerifiedUserByEmailUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.verified_user.InsertVerifiedUserUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.domain.usecase.remote.verified_user.RemoveVerifiedUserUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.verified_user.UpdateVerifiedUserUseCase
import com.natiqhaciyef.witapplication.domain_module_test.repository.FakeVerifiedUserRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test

class VerifiedUserUseCase {
    private val fakeVerifiedUserUseCase = FakeVerifiedUserRepository(
        mutableListOf(
            DefaultImpl.verifiedUserModel,
            DefaultImpl.verifiedUserModel.copy(
                id = 1,
                name = "Pirres",
                email = "pirres123@gmail.com",
                password = "Pirres123"
            )
        ),
    )

    @Test
    fun `get all verified user use case (remote) returns success`() = runTest {
        val result = GetAllVerifiedUsersUseCase(fakeVerifiedUserUseCase).invoke().last()

        assertThat(result).isEqualTo(
            Resource.success(
                mutableListOf(
                    DefaultImpl.verifiedUserModel,
                    DefaultImpl.verifiedUserModel.copy(
                        id = 1,
                        name = "Pirres",
                        email = "pirres123@gmail.com",
                        password = "Pirres123"
                    )
                )
            )
        )
    }

    @Test
    fun `get verified user by email use case (remote) returns success`() = runTest {
        val result = GetVerifiedUserByEmailUseCase(fakeVerifiedUserUseCase)
            .invoke("pirres123@gmail.com").last()

        assertThat(result).isEqualTo(
            Resource.success(
                mutableListOf(
                    DefaultImpl.verifiedUserModel.copy(
                        id = 1,
                        name = "Pirres",
                        email = "pirres123@gmail.com",
                        password = "Pirres123"
                    )
                )
            )
        )
    }

    @Test
    fun `insert verified user use case (remote) returns success`() = runTest {
        val result = InsertVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(DefaultImpl.verifiedUserModel.copy(id = 3)).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

    @Test
    fun `remove verified user use case (remote) returns success`() = runTest {
        val result = RemoveVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(1).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }

    @Test
    fun `update verified user use case (remote) returns success`() = runTest {
        val result = UpdateVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(DefaultImpl.verifiedUserModel.copy(id = 1, email = "pirressie123@gmail.com")).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.UPDATE_SUCCESS))
    }


    @Test
    fun `insert verified user use case (remote) with user contains empty field returns error`() = runTest {
        val result = InsertVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(DefaultImpl.verifiedUserModel.copy(id = 1, phone = "")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
    }

    @Test
    fun `insert verified user use case (remote) with user contains wrong email returns error`() = runTest {
        val result = InsertVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(DefaultImpl.verifiedUserModel.copy(id = 1, email = "pirressie@mail.ru")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
    }

    @Test
    fun `insert verified user use case (remote) with user contains wrong password size returns error`() = runTest {
        val result = InsertVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(DefaultImpl.verifiedUserModel.copy(id = 1, password = "pirres")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
    }

    @Test
    fun `update verified user use case (remote) with user contains empty field returns error`() = runTest {
        val result = UpdateVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(DefaultImpl.verifiedUserModel.copy(id = 1, phone = "")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
    }

    @Test
    fun `update verified user use case (remote) with user contains wrong email returns error`() = runTest {
        val result = UpdateVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(DefaultImpl.verifiedUserModel.copy(id = 1, email = "pirressie@mail.ru")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
    }

    @Test
    fun `update verified user use case (remote) with user contains wrong password size returns error`() = runTest {
        val result = UpdateVerifiedUserUseCase(fakeVerifiedUserUseCase)
            .invoke(DefaultImpl.verifiedUserModel.copy(id = 1, password = "pirres")).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.SOMETHING_WENT_WRONG, null))
    }
}