package com.cme.cmekotlin.signup.data.repo

import com.cme.cmekotlin.model.StateConfig

interface VerificationModel {
    suspend fun fetchValidStates(): Result<List<StateConfig>>
    suspend fun submitVerification(uid: String, data: Map<String, Any>): Result<Unit>
    suspend fun markUserAsVerified(uid: String): Result<Unit>

}