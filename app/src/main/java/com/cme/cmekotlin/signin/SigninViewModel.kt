package com.cme.projectcme.signin

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cme.cmekotlin.auth.AuthRepository
import com.cme.cmekotlin.auth.UserSessionManager
import com.cme.cmekotlin.model.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SigninViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository
) : AndroidViewModel(application) {
    private val session = UserSessionManager(application)
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

    fun onEmailChange(v: String) { email = v }
    fun onPasswordChange(v: String) { password = v }
    fun togglePasswordVisibility() { passwordVisible = !passwordVisible }

    fun signIn() {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) return@launch
            isLoading = true
            errorMessage = null
            val result = authRepository.signIn(email, password)
            if (result.isSuccess) {
                session.saveSession(email)
                _signInState.value = SignInState(isSuccess = true)
            } else {
                errorMessage = result.exceptionOrNull()?.localizedMessage
            }
            isLoading = false
        }
    }
}
