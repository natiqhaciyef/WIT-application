package com.natiqhaciyef.witapplication.data.source

import com.natiqhaciyef.witapplication.data.local.ContactDao
import com.natiqhaciyef.witapplication.data.models.ContactModel
import com.natiqhaciyef.witapplication.data.network.service.ContactService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactDataSource(
    private val service: ContactService,
    private val dao: ContactDao
) {

    suspend fun getAllContacts() = withContext(Dispatchers.IO) {
        service.getAllContacts()
    }

    suspend fun getAllSavedContacts() = withContext(Dispatchers.IO) {
        dao.getAllSavedContact()
    }


    suspend fun insertContact(contactModel: ContactModel) = withContext(Dispatchers.IO) {
        service.insertContact(
            name = contactModel.name,
            email = contactModel.email,
            phone = contactModel.phone,
            description = contactModel.description,
            field = contactModel.field,
            date = contactModel.date,
            isChecked = contactModel.isChecked
        )
    }

    suspend fun saveContactLocal(contactModel: ContactModel) = withContext(Dispatchers.IO){
        dao.saveContact(contactModel)
    }


    suspend fun removeContact(id: Int) = withContext(Dispatchers.IO) {
        service.removeContact(id = id)
    }

    suspend fun removeContactLocal(contactModel: ContactModel) = withContext(Dispatchers.IO){
        dao.removeContact(contactModel)
    }
}