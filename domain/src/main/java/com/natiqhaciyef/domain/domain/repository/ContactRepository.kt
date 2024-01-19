package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.domain.domain.base.BaseRepository
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ContactResult

interface ContactRepository : BaseRepository{

    suspend fun getAllContacts(): ContactResult

    suspend fun insertContact(contactModel: ContactModel): CRUDResponse

    suspend fun removeContact(id: Int): CRUDResponse

    suspend fun getAllSavedContacts(): List<ContactModel>?

    suspend fun saveContactLocal(contactModel: ContactModel)

    suspend fun removeContactLocal(contactModel: ContactModel)
}