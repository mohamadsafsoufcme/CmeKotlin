package com.cme.projectcme.signup

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)