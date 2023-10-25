package com.natiqhaciyef.witapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.witapplication.data.models.ContactModel


@Database(entities = [ContactModel::class], version = 1)
abstract class AppDatabase(): RoomDatabase(){
    abstract fun getContactDao(): ContactDao
}