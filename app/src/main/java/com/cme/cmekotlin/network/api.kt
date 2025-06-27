package com.cme.cmekotlin.network
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

data class VerificationRequest(
    val uid: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val address: String,
    val state: String,
    val zipCode: String
)

data class VerificationResponse(
    val status: String,
    val verified: Boolean,
    val message: String
)

interface MockVerificationApi {
    @POST("/verify")
    suspend fun submitVerification(@Body request: VerificationRequest): Response<VerificationResponse>
}