package com.natiqhaciyef.domain.domain.usecase.firebase

import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.domain.domain.repository.impl.FirebaseRepositoryImpl
import com.natiqhaciyef.util.models.service.InfoModel
import javax.inject.Inject

class GetAllFAQUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepositoryImpl
) {


    operator fun invoke(
        onSuccess: (List<InfoModel>) -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        val list = mutableListOf<InfoModel>()
        firebaseRepository.ds.firestore.collection("FAQ")
            .addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    for (doc in docs.reversed()) {
                        val title = doc["title"].toString()
                        val description = doc["description"].toString()

                        val infoModel = InfoModel(title = title, description = description)

                        list.add(infoModel)
                    }

                    if (list.isNotEmpty())
                        onSuccess(list)
                    else
                        onFail(Exception(ErrorMessages.DATA_NOT_FOUND))
                } else {
                    onFail(error ?: Exception(ErrorMessages.DOCUMENT_NOT_FOUND))
                }
            }
    }
}