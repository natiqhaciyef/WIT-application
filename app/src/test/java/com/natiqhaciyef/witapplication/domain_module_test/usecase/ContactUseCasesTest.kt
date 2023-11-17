package com.natiqhaciyef.witapplication.domain_module_test.usecase

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.usecase.local.contact.GetAllSavedContactsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.contact.SaveContactUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.contact.GetAllContactUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.contact.InsertContactUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.contact.RemoveContactUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.witapplication.domain_module_test.repository.FakeContactRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ContactUseCasesTest {

    private val fakeContactRepository = FakeContactRepository(mutableListOf(DefaultImpl.contactModel))

    @Test
    fun `get all contacts use case (remote) returns success`() = runTest {
        val result = GetAllContactUseCase(fakeContactRepository).invoke().last()

        assertThat(result).isEqualTo(Resource.success(listOf(DefaultImpl.contactModel)))
    }

    @Test
    fun `insert contact use case (remote) returns success`() = runTest {
        val contactModel = ContactModel(
            id = 1,
            name = "Nathan",
            email = "nathan@gmail.com",
            phone = "+994 557889214",
            description = "Now nothing selected for it",
            field = "Verification",
        )
        val result = InsertContactUseCase(fakeContactRepository).invoke(contactModel).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

    @Test
    fun `remove contact use case (remote) returns success`() = runTest {
        val result = RemoveContactUseCase(fakeContactRepository).invoke(0).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }

    @Test
    fun `get all saved contacts use case (local) returns success`() = runTest {
        val result = GetAllSavedContactsUseCase(fakeContactRepository).invoke().last()

        assertThat(result).isEqualTo(Resource.success(listOf(DefaultImpl.contactModel)))
    }

    @Test
    fun `save contact use case (local) returns success`() = runTest {
        val contactModel = ContactModel(
            id = 1,
            name = "Nathan",
            email = "nathan@gmail.com",
            phone = "+994 557889214",
            description = "Now nothing selected for it",
            field = "Verification",
        )
        val result = SaveContactUseCase(fakeContactRepository).invoke(contactModel).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

    @Test
    fun `remove saved contact use case (local) returns success`() = runTest {
        val result = RemoveContactUseCase(fakeContactRepository).invoke(0).last()

        assertThat(result).isEqualTo(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }


    @Test
    fun `insert contact use case (remote) with contact model contains empty field returns error`() = runTest {
        val contactModel = ContactModel(
            id = 1,
            name = "",
            email = "nathan@gmail.com",
            phone = "+994 557889214",
            description = "Now nothing selected for it",
            field = "Verification",
        )
        val result = InsertContactUseCase(fakeContactRepository).invoke(contactModel).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
    }

    @Test
    fun `save contact use case (local) with contact model contains empty field returns error`() = runTest {
        val contactModel = ContactModel(
            id = 1,
            name = "",
            email = "nathan@gmail.com",
            phone = "+994 557889214",
            description = "Now nothing selected for it",
            field = "Verification",
        )
        val result = SaveContactUseCase(fakeContactRepository).invoke(contactModel).last()

        assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
    }

}