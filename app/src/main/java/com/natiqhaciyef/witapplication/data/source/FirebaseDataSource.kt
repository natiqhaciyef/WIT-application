package com.natiqhaciyef.witapplication.data.source

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseDataSource(
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore
)
