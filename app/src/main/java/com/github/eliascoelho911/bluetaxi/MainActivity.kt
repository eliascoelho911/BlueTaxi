package com.github.eliascoelho911.bluetaxi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.navigation.animation.AnimatedNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavHost()
    }

    private fun setupNavHost() {
        setContent {
//            val blueTaxiState = rememberBlueTaxiState()
//            AnimatedNavHost(
//                navController = blueTaxiState.navHostController,
//                startDestination = LoginScreenRoute.route
//            ) {
//                composable(LoginScreenRoute.route) {
//                    LoginScreen()
//                }
//            }
        }
    }
}