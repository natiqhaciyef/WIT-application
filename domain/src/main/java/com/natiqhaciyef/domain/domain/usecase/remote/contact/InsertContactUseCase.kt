package com.natiqhaciyef.domain.domain.usecase.remote.contact

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository,
) {

    suspend operator fun invoke(contactModel: ContactModel) = flow {
        emit(Resource.loading(null))

        if (
            contactModel.name.isNotEmpty() &&
            contactModel.email.isNotEmpty() &&
            contactModel.field.isNotEmpty() &&
            contactModel.description.isNotEmpty() &&
            contactModel.phone.isNotEmpty()
        ) {
            val result = contactRepository.insertContact(contactModel)

            if (result.success > 0) {
                emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
            } else {
                emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
            }
        }else{
            emit(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
    }
}