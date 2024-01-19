package com.natiqhaciyef.witapplication.repository

import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
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
        if (remoteContactResult.contactResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val list = remoteContactResult.contactResult?.toMutableList()
        list?.add(contactModel)
        remoteContactResult.contactResult = list
        return if (remoteContactResult.contactResult!!.contains(contactModel))
             CRUDResponse(success = 1, message = ConfigUseCase.INSERT_SUCCESS)
        else
            CRUDResponse(success = 0, message = ConfigUseCase.INSERT_FAIL)
    }

    override suspend fun removeContact(id: Int): CRUDResponse {
        if (remoteContactResult.contactResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val list = remoteContactResult.contactResult?.toMutableList()
        val element = list?.find { it.id == id }
        list?.removeAt(id)
        remoteContactResult.contactResult = list
        return if (!remoteContactResult.contactResult!!.contains(element))
            CRUDResponse(success = 1, message = ConfigUseCase.REMOVE_SUCCESS)
        else
            CRUDResponse(success = 0, message = ConfigUseCase.REMOVE_FAIL)
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