package com.cme.projectcme.signup.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cme.cmekotlin.components.CustomInputField
import com.cme.cmekotlin.signup.ui.main.SignUpViewModel
import com.cme.cmekotlin.ui.theme.softGreen


@Composable
fun SignUpView(
    onSignInClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by remember { derivedStateOf { viewModel.state } }

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }


    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    var dialogTitle by remember { mutableStateOf("") }




    LaunchedEffect(state.isSuccess, state.error) {
        when {
            state.isSuccess -> {
                dialogTitle = "Success"
                dialogMessage = "Your account was created successfully."
                showDialog = true
            }
            state.error != null -> {
                dialogTitle = "Error"
                dialogMessage = state.error!!
                showDialog = true
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(36.dp))

            Box(
                Modifier
                    .size(72.dp)
                    .background(Color.DarkGray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Logo", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(Modifier.height(16.dp))

            Text("Create Account", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(24.dp))

            CustomInputField(username, { username = it }, "Username")
            Spacer(Modifier.height(12.dp))

            CustomInputField(email, { email = it }, "Email Address", keyboardType = KeyboardType.Email)
            Spacer(Modifier.height(12.dp))

            CustomInputField(
                value = "+1 $phone",
                onValueChange = { input -> phone = input.removePrefix("+1 ").trimStart() },
                placeholder = "Phone Number",
                keyboardType = KeyboardType.Phone
            )
            Spacer(Modifier.height(12.dp))

            CustomInputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                passwordVisible = passwordVisible,
                onToggleVisibility = { passwordVisible = !passwordVisible }
            )
            Spacer(Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = agreedToTerms,
                    onCheckedChange = { agreedToTerms = it },
                    colors = CheckboxDefaults.colors(checkedColor = softGreen)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    buildAnnotatedString {
                        append("By signing up, I agree to the ")
                        withStyle(SpanStyle(color = softGreen)) { append("Privacy Policy") }
                        append(" and the ")
                        withStyle(SpanStyle(color = softGreen)) { append("Terms of Service.") }
                    },
                    fontSize = 12.sp,
                    color = Color.White,
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { viewModel.signUp(username, email, "+1 $phone", password) },
                    enabled = agreedToTerms && !state.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (agreedToTerms) softGreen else Color.DarkGray,
                        disabledContainerColor = Color.DarkGray,
                        disabledContentColor = Color.LightGray
                    )
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Create Account", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    }
                }

                Spacer(Modifier.height(12.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Already have an account?", color = Color.Gray)
                    Spacer(Modifier.width(4.dp))
                    Text("Sign in", color = softGreen, fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable { onSignInClick() })
                }
            }

            Spacer(Modifier.height(16.dp))
        }


        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(dialogTitle) },
                text = { Text(dialogMessage) },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        if (state.isSuccess) onCreateAccountClick()
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

