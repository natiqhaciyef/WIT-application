package com.natiqhaciyef.witapplication.domain.usecase.firebase

import com.natiqhaciyef.witapplication.data.models.MaterialModel
import com.natiqhaciyef.witapplication.domain.repository.impl.FirebaseRepositoryImpl
import javax.inject.Inject

class GetAllMaterialsNameUseCase @Inject constructor(
    private val firebaseRepositoryImpl: FirebaseRepositoryImpl
) {

    suspend operator fun invoke(
        concept: String,
        onSuccess: (List<MaterialModel>) -> Unit,
        onFail: (Exception?) -> Unit,
    ) {
        val list = mutableListOf<MaterialModel>()
        firebaseRepositoryImpl.ds.firestore.collection("Materials")
            .addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    for (doc in docs) {
                        val title = doc["title"].toString()
                        val type = doc["type"].toString()
                        val field = doc["field"].toString()
                        val image = doc["image"].toString()
                        val url = doc["url"].toString()

                        if (field.contains(concept)) {
                            val material = MaterialModel(
                                title = title,
                                image = image,
                                url = url,
                                field = field,
                                type = type,
                            )
                            list.add(material)
                        }
                    }

                    onSuccess(list)
                } else {
                    onFail(error)
                }
            }
    }
}