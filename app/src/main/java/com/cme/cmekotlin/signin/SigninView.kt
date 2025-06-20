package com.cme.projectcme.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cme.cmekotlin.components.CustomInputField
import com.cme.cmekotlin.ui.theme.softGreen

@Composable
fun SigninView(
    onForgotPassword: () -> Unit,
    onCreateAccount: () -> Unit,
    viewModel: SigninViewModel = hiltViewModel()
) {
    val email by remember { derivedStateOf { viewModel.email } }
    val password by remember { derivedStateOf { viewModel.password } }
    val passwordVisible by remember { derivedStateOf { viewModel.passwordVisible } }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Spacer(Modifier.weight(1.5f))
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .size(100.dp)
                    .background(softGreen, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Logo", color = Color.White, fontSize = 18.sp)
            }
            Spacer(Modifier.height(16.dp))
            Text("Sign in", color = Color.White, fontSize = 24.sp)
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
                isPassword = true,
                passwordVisible = passwordVisible,
                onToggleVisibility = viewModel::togglePasswordVisibility
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Forgot password?",
                color = Color.LightGray,
                modifier = Modifier
                    .clickable { onForgotPassword() }
                    .padding(top = 8.dp)
            )
        }
        Spacer(Modifier.weight(1f))
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
            Text("Sign in")
        }
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have an account? ", color = Color.White)
            Text(
                "Create Account",
                color = softGreen,
                modifier = Modifier.clickable { onCreateAccount() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginViewPreview() {
    SigninView(onForgotPassword = {}, onCreateAccount = {})
}
