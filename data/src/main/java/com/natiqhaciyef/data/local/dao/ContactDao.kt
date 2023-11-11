package com.natiqhaciyef.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.natiqhaciyef.util.models.ContactModel

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact_table")
    suspend fun getAllSavedContact(): List<ContactModel>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveContact(contactModel: ContactModel)

    @Delete
    suspend fun removeContact(contactModel: ContactModel)

}