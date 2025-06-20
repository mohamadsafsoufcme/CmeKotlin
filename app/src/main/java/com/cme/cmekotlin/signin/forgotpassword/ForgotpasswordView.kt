package com.cme.cmekotlin.signin.forgotpassword
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cme.cmekotlin.auth.ForgotPasswordViewModel
import com.cme.projectcme.signin.forgotpassword.ForgotPasswordViewModel

@Composable
fun ForgotPasswordView(
    onBack: () -> Unit,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val email by remember { derivedStateOf { viewModel.email } }
    val isLoading by remember { derivedStateOf { viewModel.isLoading } }
    val message by remember { derivedStateOf { viewModel.message } }
    val softGreen = Color(0xFF00C853)

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Text("Reset Password", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value = email,
            onValueChange = viewModel::onEmailChange,
            placeholder = { Text("Email Address", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            shape = RoundedCornerShape(28.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = softGreen,
                unfocusedBorderColor = Color.Gray,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White
            )
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = viewModel::sendReset,
            enabled = !isLoading && email.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (email.isNotBlank()) softGreen else Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            if (isLoading) CircularProgressIndicator(Modifier.size(24.dp), color = Color.White)
            else Text("Send Reset Link")
        }
        Spacer(Modifier.height(16.dp))
        message?.let {
            Text(it, color = if (it.startsWith("Reset")) softGreen else Color.Red)
            Spacer(Modifier.height(16.dp))
        }
        TextButton(onClick = onBack) {
            Text("Back to Sign In", color = Color.White)
        }
        Spacer(Modifier.weight(2f))
    }
}

@Composable
@Preview(showBackground = true)
private fun ForgotPasswordViewPreview() {
    ForgotPasswordView(onBack = {})
}
