package com.github.eliascoelho911.bluetaxi.passenger

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.github.eliascoelho911.bluetaxi.navigation.Screen
import com.github.eliascoelho911.bluetaxi.navigation.ScreenGroup
import com.github.eliascoelho911.bluetaxi.ui.login.LoginScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun PassengerRootNavGraph(passengerState: PassengerState = rememberPassengerState()) {
    AnimatedNavHost(
        navController = passengerState.navHostController,
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
        LoginScreen(onUserLogIn = {

        })
    }
}
