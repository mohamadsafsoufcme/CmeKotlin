package com.cme.cmekotlin

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cme.cmekotlin.signin.forgotpassword.ForgotPasswordView
import com.cme.projectcme.signin.SigninView
import com.cme.projectcme.signup.SignUpView
import com.cme.projectcme.signup.VerificationView
@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "signin",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("signin") {
                SigninView(
                    onForgotPassword = {
                        navController.navigate("forgot_password")
                    },
                    onCreateAccount = {
                        navController.navigate("signup")
                    }
                )
            }

            composable("signup") {
                SignUpView(
                    onSignInClick = {
                        navController.popBackStack()
                    },
                    onCreateAccountClick = {
                        navController.navigate("verification")
                    }
                )
            }

            composable("verification") {
                VerificationView(
                    onVerify = {
                        // Navigate to home or dashboard
                    },
                    onSkip = {
                        // Handle skip
                    }
                )
            }

            composable("forgot_password") {
                ForgotPasswordView(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

