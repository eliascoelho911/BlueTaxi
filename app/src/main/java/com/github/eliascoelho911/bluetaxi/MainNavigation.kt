package com.github.eliascoelho911.bluetaxi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.github.eliascoelho911.bluetaxi.auth.login.LoginScreen
import com.github.eliascoelho911.bluetaxi.commons.BlueTaxiState
import com.github.eliascoelho911.bluetaxi.commons.rememberBlueTaxiState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

private sealed class ScreenGroup(val route: String) {
    object Auth: ScreenGroup("auth")
}

private sealed class Screen(partialRoute: String, group: ScreenGroup) {
    val route = "${group.route}/$partialRoute"

    object Login : Screen(partialRoute = "login", group = ScreenGroup.Auth)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun BlueTaxiNavGraph(blueTaxiState: BlueTaxiState = rememberBlueTaxiState()) {
    AnimatedNavHost(
        navController = blueTaxiState.navHostController,
        startDestination = ScreenGroup.Auth.route
    ) {
        addAuthNavigation()
    }
}

private fun NavGraphBuilder.addAuthNavigation() {
    navigation(startDestination = Screen.Login.route, route = ScreenGroup.Auth.route) {
        addLoginScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addLoginScreen() {
    composable(Screen.Login.route) {
        LoginScreen()
    }
}
