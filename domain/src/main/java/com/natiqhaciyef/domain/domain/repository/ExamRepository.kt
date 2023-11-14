package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.data.network.result.CRUDResponse
import com.natiqhaciyef.data.network.result.ExamResult
import com.natiqhaciyef.util.models.ExamModel

interface ExamRepository {

    suspend fun getAllExams(): ExamResult

    suspend fun insertExam(examModel: ExamModel): CRUDResponse

    suspend fun getAllParticipatedExams(): List<ExamModel>?

    suspend fun participateExam(examModel: ExamModel)

    suspend fun removeParticipation(examModel: ExamModel)
}