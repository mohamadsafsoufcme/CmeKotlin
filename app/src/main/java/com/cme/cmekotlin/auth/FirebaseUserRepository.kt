package com.cme.cmekotlin.auth

import com.cme.cmekotlin.model.StateConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseUserRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : UserRepository {
    override suspend fun fetchValidStates(): Result<List<StateConfig>> = runCatching {
        firestore.collection("configs")
            .whereEqualTo("valid", true)
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(StateConfig::class.java) }
    }

    override suspend fun submitVerification(uid: String, data: Map<String, Any>): Result<Unit> = runCatching {
        firestore.collection("users")
            .document(uid)
            .update(data)
            .await()
    }
}