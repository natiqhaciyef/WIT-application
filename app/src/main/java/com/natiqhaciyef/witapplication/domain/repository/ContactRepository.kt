package com.natiqhaciyef.witapplication.domain.repository

import com.natiqhaciyef.witapplication.data.models.ContactModel
import com.natiqhaciyef.witapplication.data.network.result.CRUDResponse
import com.natiqhaciyef.witapplication.data.network.result.ContactResult

interface ContactRepository {

    suspend fun getAllContacts(): ContactResult

    suspend fun getAllSavedContacts(): List<ContactModel>?

    suspend fun insertContact(contactModel: ContactModel): CRUDResponse

    suspend fun saveContactLocal(contactModel: ContactModel)

    suspend fun removeContact(id: Int): CRUDResponse

    suspend fun removeContactLocal(contactModel: ContactModel)
}