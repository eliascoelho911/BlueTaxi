package com.github.eliascoelho911.bluetaxi.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class NavigationController(
    val navHostController: NavHostController
) {
    val hasBackQueue: Boolean
        get() = navHostController.backQueue.isNotEmpty()

    fun popBackStack() {
        navHostController.popBackStack()
    }

    fun navigateToWelcomeScreen() {
        navHostController.navigate(Screen.Welcome.route)
    }

    fun navigateToLoginScreen() {
        navHostController.navigate(Screen.Login.route)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberNavigationController(
    navHostController: NavHostController = rememberAnimatedNavController(),
) = remember(navHostController) {
    NavigationController(navHostController)
}