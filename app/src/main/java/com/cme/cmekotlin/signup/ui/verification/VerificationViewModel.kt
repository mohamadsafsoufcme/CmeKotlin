package com.cme.projectcme.signup.ui.verification

import android.widget.Toast
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cme.cmekotlin.model.StateConfig
import com.cme.cmekotlin.signup.data.repo.VerificationModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val userRepo: VerificationModel,
    private val auth: FirebaseAuth
) : ViewModel() {

    var states by mutableStateOf<List<StateConfig>>(emptyList())
        private set

    var expanded by mutableStateOf(false)
        private set

    var firstName by mutableStateOf("")
    var lastName  by mutableStateOf("")
    var dob       by mutableStateOf("")
    var address   by mutableStateOf("")
    var stateVal  by mutableStateOf("")
    var zipCode   by mutableStateOf("")
    var agreed    by mutableStateOf(false)

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var success by mutableStateOf(false)
        private set

    init {
        loadStates()
    }


    private fun loadStates() {
        viewModelScope.launch {
            runCatching {
                userRepo.fetchValidStates()
            }.onSuccess { result ->
                states = result.getOrDefault(emptyList())
            }
        }
    }

    fun onSpinnerToggle() {
        expanded = !expanded
    }

    fun onStateSelected(code: String) {
        stateVal = code
        expanded = false
    }

    fun onAgreeChange(value: Boolean) {
        agreed = value
    }

    fun submitVerification() {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            isLoading = true
            error = null
            val data = mapOf(
                "firstName"   to firstName,
                "lastName"    to lastName,
                "dateOfBirth" to dob,
                "address"     to address,
                "state"       to stateVal,
                "zipCode"     to zipCode
            )
            runCatching {
                userRepo.submitVerification(uid, data)
                userRepo.markUserAsVerified(uid).getOrThrow()
            }.onSuccess {
                success = true
            }.onFailure {
                error = it.localizedMessage
            }
            isLoading = false
        }
    }
}