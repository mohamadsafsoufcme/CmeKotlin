package com.cme.cmekotlin.model

import com.google.firebase.Timestamp

data class User(
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    val createdAt: Timestamp = Timestamp.now(),
    val firstName: String? = null,
    val lastName: String? = null,
    val dateOfBirth: String? = null,
    val address: String? = null,
    val state: String? = null,
    val zipCode: String? = null
)
