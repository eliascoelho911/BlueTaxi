package com.github.eliascoelho911.bluetaxi.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class BlueTaxiNavigationController(
    val navHostController: NavHostController
) {
    val popBackStackOrNull: (() -> Unit)?
        get() = if (navHostController.backQueue.isNotEmpty()) {
            { navHostController.popBackStack() }
        } else null

    fun navigateToWelcomeScreen() {
        navHostController.navigate(Screen.Welcome.route)
    }

    fun navigateToLoginScreen() {
        navHostController.navigate(Screen.Login.route)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberBlueTaxiNavigationController(
    navHostController: NavHostController = rememberAnimatedNavController(),
) = remember(navHostController) {
    BlueTaxiNavigationController(navHostController)
}