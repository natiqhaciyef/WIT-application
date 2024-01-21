package com.natiqhaciyef.domain.domain.usecase.firebase

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.base.FAQ_DIRECTORY
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.service.InfoLangModel
import com.natiqhaciyef.util.models.service.InfoModel
import javax.inject.Inject

class GetAllFAQUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {


    operator fun invoke(
        onSuccess: (List<InfoLangModel>) -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        val list = mutableListOf<InfoLangModel>()
        repository.firestore().collection(FAQ_DIRECTORY)
            .addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    for (doc in docs.reversed()) {
                        val title = doc["title"].toString()
                        val description = doc["description"].toString()
                        val titleAz = doc["titleAz"].toString()
                        val descriptionAz = doc["descriptionAz"].toString()
                        val titleTr = doc["titleTr"].toString()
                        val descriptionTr = doc["descriptionTr"].toString()


                        val infoModel = InfoLangModel(
                            title = title,
                            description = description,
                            titleAz = titleAz,
                            descriptionAz = descriptionAz,
                            titleEng = title,
                            descriptionEng = description,
                            titleTr = titleTr,
                            descriptionTr = descriptionTr
                        )

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