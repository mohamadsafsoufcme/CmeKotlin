package com.cme.cmekotlin.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signUp(email: String, password: String): Result<String?> = try {
        val result = firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .await()
        Result.success(result.user?.uid)
    } catch (e: Exception) {
        Result.failure(e)
    }
    override suspend fun signIn(email: String, password: String): Result<String?> = try {
        val result = firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .await()
        Result.success(result.user?.uid)
    } catch (e: Exception) {
        Result.failure(e)
    }
    override suspend fun sendPasswordReset(email: String): Result<Void?> = try {
        val result = firebaseAuth
            .sendPasswordResetEmail(email)
            .await()
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
