package com.natiqhaciyef.data.source

import com.natiqhaciyef.data.network.service.ExamService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExamDataSource(val service: ExamService) {

    suspend fun getAllExams() = withContext(Dispatchers.IO) {
        service.getAllExams()
    }
}