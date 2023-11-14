package com.natiqhaciyef.domain.domain.repository.impl

import com.natiqhaciyef.data.network.result.CRUDResponse
import com.natiqhaciyef.data.network.result.ExamResult
import com.natiqhaciyef.data.source.ExamDataSource
import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.util.models.ExamModel

class ExamRepositoryImpl(val ds: ExamDataSource) : ExamRepository {
    override suspend fun getAllExams(): ExamResult = ds.getAllExams()

    override suspend fun insertExam(examModel: ExamModel): CRUDResponse = ds.insertExam(examModel)
}