package com.natiqhaciyef.domain.domain.usecase.firebase

import com.natiqhaciyef.domain.domain.repository.impl.FirebaseRepositoryImpl
import com.natiqhaciyef.util.models.service.NotificationModel
import javax.inject.Inject

class GetAllNotificationsUseCase @Inject constructor(
    private val firebaseRepositoryImpl: FirebaseRepositoryImpl,
) {

    operator fun invoke(
        onSuccess: (List<NotificationModel>) -> Unit = {},
        onFail: (Exception?) -> Unit = {},
        onLoading: () -> Unit = { },
    ) {
        onLoading()
        val list = mutableListOf<NotificationModel>()
        firebaseRepositoryImpl.ds.firestore.collection("Notifications")
            .addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    for (doc in docs) {
                        val title: String = doc["title"].toString()
                        val description: String = doc["description"].toString()
                        val isActive: Boolean = doc["isActive"].toString().toBoolean()

                        val notification = NotificationModel(
                            title = title,
                            description = description,
                            isActive = isActive
                        )
                        list.add(notification)
                    }

                    onSuccess(list)
                } else {
                    onFail(error)
                }
            }
    }
}