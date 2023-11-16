package com.natiqhaciyef.witapplication.domain_module_test.repository

import com.natiqhaciyef.domain.domain.repository.ContactRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ContactResult

class FakeContactRepository(private val list: MutableList<ContactModel>?) : ContactRepository{

    private val remoteContactResult: ContactResult = ContactResult(list)
    private val localContactList = list

    override suspend fun getAllContacts(): ContactResult {
        return remoteContactResult
    }

    override suspend fun insertContact(contactModel: ContactModel): CRUDResponse {
        val list = remoteContactResult.contactResult?.toMutableList()
        val count = list?.size ?: 0
        list?.add(contactModel)
        remoteContactResult.contactResult = list
        return if (remoteContactResult.contactResult != null && count < remoteContactResult.contactResult!!.size)
             CRUDResponse(success = 1, message = BaseUseCase.INSERT_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.INSERT_FAIL)
    }

    override suspend fun removeContact(id: Int): CRUDResponse {
        val list = remoteContactResult.contactResult?.toMutableList()
        val count = list?.size ?: 0
        list?.removeAt(id)
        remoteContactResult.contactResult = list
        return if (remoteContactResult.contactResult != null && count > remoteContactResult.contactResult!!.size)
            CRUDResponse(success = 1, message = BaseUseCase.REMOVE_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.REMOVE_FAIL)
    }

    override suspend fun getAllSavedContacts(): List<ContactModel>? {
        return localContactList
    }

    override suspend fun saveContactLocal(contactModel: ContactModel) {
        localContactList?.add(contactModel)
    }

    override suspend fun removeContactLocal(contactModel: ContactModel) {
        localContactList?.remove(contactModel)
    }
}