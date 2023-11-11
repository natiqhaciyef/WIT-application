package com.natiqhaciyef.domain.domain.usecase.remote.contact

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(contactModel: ContactModel) = flow {
        emit(Resource.loading(null))

        val result = contactRepository.insertContact(contactModel)

        if (result.success > 0) {
            emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
        } else {
            emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
        }

    }

}