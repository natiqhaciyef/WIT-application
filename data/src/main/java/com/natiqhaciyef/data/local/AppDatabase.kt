package com.natiqhaciyef.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.data.local.dao.ContactDao
import com.natiqhaciyef.data.local.dao.InterviewQuestionDao
import com.natiqhaciyef.data.local.dao.PostDao
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.util.models.PostModel


@Database(entities = [ContactModel::class, PostModel::class, InterviewQuestionModel::class], version = 3)
abstract class AppDatabase: RoomDatabase(){
    abstract fun getContactDao(): ContactDao

    abstract fun getPostDao(): PostDao

    abstract fun getInterviewQuestionDao(): InterviewQuestionDao
}