package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.domain.domain.base.BaseRepository
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ExamResult
import com.natiqhaciyef.util.models.ExamModel

interface ExamRepository : BaseRepository{

    suspend fun getAllExams(): ExamResult

    suspend fun insertExam(examModel: ExamModel): CRUDResponse

    suspend fun getAllParticipatedExams(): List<ExamModel>?

    suspend fun participateExam(examModel: ExamModel)

    suspend fun removeParticipation(examModel: ExamModel)
}