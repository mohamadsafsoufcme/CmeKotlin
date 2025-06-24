package com.cme.projectcme.signin

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cme.cmekotlin.auth.AuthRepository
import com.cme.cmekotlin.model.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _signInState = MutableStateFlow(SignInState())
    val signInState: StateFlow<SignInState> = _signInState
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var passwordVisible by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isSignedIn by mutableStateOf(false)
        private set

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun signIn() {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) return@launch
            isLoading = true
            errorMessage = null
            val result = authRepository.signIn(email, password)
            isSignedIn = result.isSuccess
            if (result.isSuccess) {
                Log.d("LoginViewModel", "Sign in successful for $email")
                _signInState.value = SignInState(isSuccess = true)
            } else {
                errorMessage = result.exceptionOrNull()?.localizedMessage
            }
            isLoading = false
        }
    }


}
