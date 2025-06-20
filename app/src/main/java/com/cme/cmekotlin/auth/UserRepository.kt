package com.cme.cmekotlin.auth

import com.cme.projectcme.model.StateConfig
import com.google.firebase.auth.FirebaseAuth

interface UserRepository {
    suspend fun fetchValidStates(): Result<List<StateConfig>>
    suspend fun submitVerification(uid: String, data: Map<String, Any>): Result<Unit>
}