package com.bluetaxi.passenger.login.presentation

import androidx.compose.runtime.Composable
import com.bluetaxi.driver.navigation.NavigationController

@Composable
fun LoginScreen(navigationController: NavigationController) {
    com.bluetaxi.login.presentation.LoginScreen(
        navHostController = navigationController.navHostController,
        onNavigateToHome = { /*TODO*/ },
        onNavigateToSignUp = { navigationController.navigateToSignUp() })
}