package com.cme.cmekotlin.signup.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cme.cmekotlin.auth.AuthRepository
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val firestore: FirebaseFirestore
) : ViewModel() {
    var state by mutableStateOf(SignUpState())
        private set

    fun signUp(username: String, email: String, phone: String, password: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            val authResult = authRepository.signUp(email, password)
            if (authResult.isSuccess) {
                val uid = authResult.getOrNull() ?: return@launch
                val userData = mapOf(
                    "username" to username,
                    "email" to email,
                    "phone" to phone,
                    "createdAt" to Timestamp.Companion.now()
                )
                val fsResult = runCatching {
                    firestore.collection("users").document(uid).set(userData).await()
                }
                state = if (fsResult.isSuccess) {
                    state.copy(isSuccess = true, isLoading = false)
                } else {
                    state.copy(error = fsResult.exceptionOrNull()?.localizedMessage, isLoading = false)
                }
            } else {
                state = state.copy(error = authResult.exceptionOrNull()?.localizedMessage, isLoading = false)
            }
        }
    }
}