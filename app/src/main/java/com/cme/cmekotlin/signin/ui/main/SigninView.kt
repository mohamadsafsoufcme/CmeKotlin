package com.cme.projectcme.signin.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cme.cmekotlin.signin.data.AppManager
import com.cme.cmekotlin.components.CustomInputField
import com.cme.cmekotlin.signin.ui.main.SigninViewModel
import com.cme.cmekotlin.ui.theme.softGreen
import kotlinx.coroutines.launch

@Composable
fun SigninView(
    onForgotPassword: () -> Unit,
    onCreateAccount: () -> Unit,
    onSignInSuccess: () -> Unit,
    viewModel: SigninViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val email by remember { derivedStateOf { viewModel.email } }
    val password by remember { derivedStateOf { viewModel.password } }
    val signInState by viewModel.signInState.collectAsState()
    val isLoading by remember { derivedStateOf { viewModel.isLoading } }
    val error by remember { derivedStateOf { viewModel.errorMessage } }
    val passwordVisible by remember { derivedStateOf { viewModel.passwordVisible } }

    LaunchedEffect(signInState.isSuccess) {
        if (signInState.isSuccess) {
            scope.launch { AppManager(context).saveSession(email) }
            onSignInSuccess()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = softGreen
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(softGreen, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Logo", color = Color.White, fontSize = 18.sp)
                }
                Spacer(Modifier.height(16.dp))
                Text(text = "Sign in", color = Color.White, fontSize = 24.sp)
                Spacer(Modifier.height(16.dp))
                CustomInputField(
                    value = email,
                    onValueChange = viewModel::onEmailChange,
                    placeholder = "Email Address",
                    keyboardType = KeyboardType.Email
                )
                Spacer(Modifier.height(12.dp))
                CustomInputField(
                    value = password,
                    onValueChange = viewModel::onPasswordChange,
                    placeholder = "Password",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onToggleVisibility = viewModel::togglePasswordVisibility
                )
                if (error != null) {
                    Text(
                        text = error!!,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Forgot password?",
                    color = Color.LightGray,
                    modifier = Modifier
                        .clickable { onForgotPassword() }
                        .padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { viewModel.signIn() },
                enabled = email.isNotBlank() && password.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (email.isNotBlank() && password.isNotBlank()) softGreen else Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Sign in")
            }
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account? ", color = Color.White)
                Text(
                    text = "Create Account",
                    color = softGreen,
                    modifier = Modifier.clickable { onCreateAccount() }
                )
            }
        }
    }
}
