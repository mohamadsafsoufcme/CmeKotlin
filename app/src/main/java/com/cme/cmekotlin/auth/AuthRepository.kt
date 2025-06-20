package com.cme.cmekotlin.auth

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Result<String?>
    suspend fun signIn(email: String, password: String): Result<String?>
    suspend fun sendPasswordReset(email: String): Result<Void?>
}
