package com.cme.cmekotlin.Verification.verification.repo

import com.cme.cmekotlin.model.StateConfig
import com.cme.cmekotlin.network.MockVerificationApi
import com.cme.cmekotlin.network.VerificationRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

class VerificationRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val api: MockVerificationApi
) : VerificationModel {
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
            .set(data, SetOptions.merge())
            .await()
        val request = VerificationRequest(
            uid = uid,
            firstName = data["firstName"] as String,
            lastName = data["lastName"] as String,
            dateOfBirth = data["dateOfBirth"] as String,
            address = data["address"] as String,
            state = data["state"] as String,
            zipCode = data["zipCode"] as String
        )

        val response = api.submitVerification(request)
        if (!response.isSuccessful || response.body()?.verified != true) {
            throw Exception("Verification failed: ${response.errorBody()?.string()}")
        }
        firestore.collection("users")
            .document(uid)
            .update("isVerified", true)
            .await()
    }
    override suspend fun markUserAsVerified(uid: String): Result<Unit> = runCatching {
        firestore.collection("users")
            .document(uid)
            .update("isVerified", true)
            .await()
    }

}