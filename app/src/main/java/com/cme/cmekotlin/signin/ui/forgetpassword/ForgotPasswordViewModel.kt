package com.cme.cmekotlin.signin.ui.forgotpassword
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cme.cmekotlin.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ForgotPasswordVM"

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    fun onEmailChange(value: String) {
        email = value
        Log.d(TAG, "Email input changed to: $email")
    }

    fun sendReset() {
        viewModelScope.launch {
            if (email.isBlank()) {
                Log.w(TAG, "sendReset called with blank email")
                return@launch
            }
            isLoading = true
            message = null
            Log.d(TAG, "Sending password reset to $email")
            try {
                val result = authRepository.sendPasswordReset(email)
                if (result.isSuccess) {
                    Log.i(TAG, "Password reset request successful for $email")
                    message = "Reset link sent to $email"
                } else {
                    val err = result.exceptionOrNull()
                    Log.e(TAG, "Password reset failed for $email", err)
                    message = err?.localizedMessage ?: "Unknown error"
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception during password reset request", e)
                message = e.localizedMessage ?: "Exception occurred"
            } finally {
                isLoading = false
                Log.d(TAG, "sendReset completed, isLoading=false")
            }
        }
    }
}