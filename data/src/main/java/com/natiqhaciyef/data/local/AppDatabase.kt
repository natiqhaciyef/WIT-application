package com.natiqhaciyef.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.data.local.dao.ContactDao
import com.natiqhaciyef.data.local.dao.ExamDao
import com.natiqhaciyef.data.local.dao.InterviewQuestionDao
import com.natiqhaciyef.data.local.dao.PostDao
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.util.models.ExamModel
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.util.models.PostModel


@Database(entities = [ContactModel::class, PostModel::class, InterviewQuestionModel::class, ExamModel::class], version = 4)
abstract class AppDatabase: RoomDatabase(){
    abstract fun getContactDao(): ContactDao

    abstract fun getPostDao(): PostDao

    abstract fun getInterviewQuestionDao(): InterviewQuestionDao

    abstract fun getExamDao(): ExamDao
}