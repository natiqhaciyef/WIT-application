package com.natiqhaciyef.domain.domain.usecase.remote.contact

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertContactUseCase @Inject constructor(
    contactRepository: ContactRepository
): BaseUseCase<ContactRepository>(contactRepository) {

    suspend operator fun invoke(contactModel: ContactModel) = flow {
        emit(Resource.loading(null))

        if (
            contactModel.name.isNotEmpty() &&
            contactModel.email.isNotEmpty() &&
            contactModel.field.isNotEmpty() &&
            contactModel.description.isNotEmpty() &&
            contactModel.phone.isNotEmpty()
        ) {
            val result = repository.insertContact(contactModel)

            if (result.success > 0) {
                emit(Resource.success(ConfigUseCase.INSERT_SUCCESS))
            } else {
                emit(Resource.error(ConfigUseCase.INSERT_FAIL, null))
            }
        }else{
            emit(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
    }
}