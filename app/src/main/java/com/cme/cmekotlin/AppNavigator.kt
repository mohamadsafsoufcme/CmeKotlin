package com.cme.cmekotlin

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cme.cmekotlin.auth.UserSessionManager
import com.cme.cmekotlin.homeview.LeagueHomeScreen
import com.cme.cmekotlin.signin.forgotpassword.ForgotPasswordView
import com.cme.projectcme.signin.SigninView
import com.cme.projectcme.signup.SignUpView
import com.cme.projectcme.signup.VerificationView
import kotlinx.coroutines.launch

@Composable
fun AppNavigator() {
    val context = LocalContext.current
    val navController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }
    LaunchedEffect(Unit) {
        isLoggedIn = UserSessionManager(context).isLoggedIn()
    }

    if (isLoggedIn == null) return

    Scaffold(Modifier.fillMaxSize()) { padding ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn == true) "home" else "signin",
            modifier = Modifier.padding(padding)
        ) {
            composable("signin") {
                SigninView(
                    onForgotPassword = { navController.navigate("forgot_password") },
                    onCreateAccount  = { navController.navigate("signup") },
                    onSignInSuccess  = {
                        navController.navigate("home") {
                            popUpTo("signin") { inclusive = true }
                        }
                    }
                )
            }
            composable("signup") {
                SignUpView(
                    onSignInClick       = { navController.popBackStack() },
                    onCreateAccountClick = { navController.navigate("verification") }
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
                ForgotPasswordView(onBack = { navController.popBackStack() })
            }
            composable("home") {
                LeagueHomeScreen()
            }
        }
    }
}
