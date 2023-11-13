package com.natiqhaciyef.domain.domain.usecase.local.contact

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke(contactModel: ContactModel) = flow{
        emit(Resource.loading(null))

        contactRepository.saveContactLocal(contactModel)
        emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

}