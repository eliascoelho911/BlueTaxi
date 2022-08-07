@file:OptIn(ExperimentalAnimationApi::class)

package com.bluetaxi.driver

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.bluetaxi.commons.android.navigation.transitions.transitionSlideInHorizontal
import com.bluetaxi.passenger.login.presentation.LoginScreen
import com.bluetaxi.driver.navigation.NavigationController
import com.bluetaxi.driver.navigation.Screen
import com.bluetaxi.driver.navigation.rememberNavigationController
import com.bluetaxi.passenger.driver.presentation.WelcomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun DriverRootNavGraph(navigationController: NavigationController = rememberNavigationController()) {
    AnimatedNavHost(
        navController = navigationController.navHostController,
        startDestination = Screen.Welcome.route
    ) {
        addWelcomeScreen(navigationController)
        addLoginScreen(navigationController)
    }
}

private fun NavGraphBuilder.addWelcomeScreen(navigationController: NavigationController) {
    composable(
        route = Screen.Welcome.route,
        popEnterTransition = { transitionSlideInHorizontal() }
    ) {
        WelcomeScreen(navigationController)
    }
}

private fun NavGraphBuilder.addLoginScreen(navigationController: NavigationController) {
    composable(
        route = Screen.Login.route,
        enterTransition = { transitionSlideInHorizontal() }
    ) {
        LoginScreen(navigationController)
    }
}