package com.natiqhaciyef.witapplication.domain.usecase.remote.contact

import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.witapplication.data.models.ContactModel
import com.natiqhaciyef.witapplication.domain.repository.ContactRepository
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