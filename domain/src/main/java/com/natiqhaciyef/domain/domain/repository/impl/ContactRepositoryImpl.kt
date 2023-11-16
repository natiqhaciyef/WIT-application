package com.natiqhaciyef.domain.domain.repository.impl

import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ContactResult
import com.natiqhaciyef.data.source.ContactDataSource
import com.natiqhaciyef.domain.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val ds: ContactDataSource
) : ContactRepository {
    override suspend fun getAllContacts(): ContactResult = ds.getAllContacts()

    override suspend fun getAllSavedContacts(): List<ContactModel>? = ds.getAllSavedContacts()

    override suspend fun insertContact(contactModel: ContactModel): CRUDResponse =
        ds.insertContact(contactModel)

    override suspend fun saveContactLocal(contactModel: ContactModel) =
        ds.saveContactLocal(contactModel)

    override suspend fun removeContact(id: Int): CRUDResponse = ds.removeContact(id)

    override suspend fun removeContactLocal(contactModel: ContactModel) =
        ds.removeContactLocal(contactModel)


}