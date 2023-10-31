package com.natiqhaciyef.witapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.witapplication.data.local.dao.ContactDao
import com.natiqhaciyef.witapplication.data.local.dao.InterviewQuestionDao
import com.natiqhaciyef.witapplication.data.local.dao.PostDao
import com.natiqhaciyef.witapplication.data.models.ContactModel
import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.data.models.PostModel


@Database(entities = [ContactModel::class, PostModel::class, InterviewQuestionModel::class], version = 3)
abstract class AppDatabase: RoomDatabase(){
    abstract fun getContactDao(): ContactDao

    abstract fun getPostDao(): PostDao

    abstract fun getInterviewQuestionDao(): InterviewQuestionDao
}