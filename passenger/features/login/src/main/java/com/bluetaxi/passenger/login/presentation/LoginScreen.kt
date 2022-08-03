package com.bluetaxi.passenger.login.presentation

import androidx.compose.runtime.Composable
import com.github.eliascoelho911.bluetaxi.navigation.NavigationController
import com.github.eliascoelho911.bluetaxi.navigation.rememberNavigationController

@Composable
fun LoginScreen(navigationController: NavigationController = rememberNavigationController()) {
    com.bluetaxi.login.presentation.LoginScreen(
        onUserLogIn = { /*TODO*/ },
        onClickSignUp = { /*TODO*/ })
}