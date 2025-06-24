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
import com.cme.cmekotlin.HomeView.LeagueHomeScreen

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
                    onForgotPassword = { navController.navigate("forgot_password") },
                    onCreateAccount = { navController.navigate("signup") },
                    onSignInSuccess = {
                        navController.navigate("home") {
                            popUpTo("signin") { inclusive = true }
                        }
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
                        navController.navigate("home") {
                            popUpTo("signin") { inclusive = true }
                        }
                    },
                    onSkip = {
                        navController.navigate("home") {
                            popUpTo("signin") { inclusive = true }
                        }
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
            composable("home") {
                LeagueHomeScreen()
            }
        }

    }
}

