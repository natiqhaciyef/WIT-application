package com.natiqhaciyef.witapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.witapplication.data.models.ContactModel
import com.natiqhaciyef.witapplication.data.models.PostModel


@Database(entities = [ContactModel::class, PostModel::class], version = 2)
abstract class AppDatabase: RoomDatabase(){
    abstract fun getContactDao(): ContactDao

    abstract fun getPostDao(): PostDao
}