package com.natiqhaciyef.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.natiqhaciyef.util.models.InterviewQuestionModel

@Dao
interface InterviewQuestionDao {

    @Query("SELECT * FROM interview_question_table")
    suspend fun getAllSavedInterviewQuestions(): List<InterviewQuestionModel>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveInterviewQuestion(interviewQuestionModel: InterviewQuestionModel)

    @Delete
    suspend fun removeSavedInterviewQuestion(interviewQuestionModel: InterviewQuestionModel)
}