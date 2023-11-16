package com.natiqhaciyef.domain.domain.repository.impl

import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ExamResult
import com.natiqhaciyef.data.source.ExamDataSource
import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.util.models.ExamModel

class ExamRepositoryImpl(val ds: ExamDataSource) : ExamRepository {
    override suspend fun getAllExams(): ExamResult = ds.getAllExams()

    override suspend fun insertExam(examModel: ExamModel): CRUDResponse = ds.insertExam(examModel)

    override suspend fun getAllParticipatedExams(): List<ExamModel>? = ds.getAllParticipatedExams()

    override suspend fun participateExam(examModel: ExamModel) = ds.participateExam(examModel)

    override suspend fun removeParticipation(examModel: ExamModel) = ds.removeParticipation(examModel)
}