package com.natiqhaciyef.witapplication.data.source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseDataSource(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore,
    val storage: FirebaseStorage
)
