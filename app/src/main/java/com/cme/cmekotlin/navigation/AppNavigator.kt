package com.cme.cmekotlin.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cme.cmekotlin.homeview.VerseHomeView
import com.example.fantasy.ui.LeagueMatchupView
import com.cme.cmekotlin.signin.data.AppManager
import com.cme.projectcme.signin.ui.main.SigninView
import com.cme.projectcme.signup.ui.main.SignUpView
import com.cme.projectcme.signup.ui.verification.VerificationView
import com.cme.cmekotlin.signin.ui.forgetpassword.ForgotPasswordView

@Composable
fun AppNavigator() {
    val context = LocalContext.current
    val navController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(Unit) {
        isLoggedIn = AppManager(context).isLoggedIn()
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
                    onSignInClick = { navController.popBackStack() },
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
                VerseHomeView(
                    onLeagueClick = {
                        navController.navigate("league_matchup")
                    }
                )
            }
            composable("league_matchup") {
                LeagueMatchupView(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
