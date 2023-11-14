package com.natiqhaciyef.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.natiqhaciyef.util.models.ExamModel

@Dao
interface ExamDao {

    @Query("SELECT * FROM exam_table")
    suspend fun getAllParticipatedExams(): List<ExamModel>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun participateExam(examModel: ExamModel)

    @Delete
    suspend fun removeParticipation(examModel: ExamModel)
}