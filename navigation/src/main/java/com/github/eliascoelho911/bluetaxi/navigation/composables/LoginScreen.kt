package com.github.eliascoelho911.bluetaxi.navigation.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.github.eliascoelho911.bluetaxi.navigation.BlueTaxiNavigationController
import com.github.eliascoelho911.bluetaxi.navigation.Screen
import com.github.eliascoelho911.bluetaxi.navigation.transitions.transitionSlideInHorizontal
import com.github.eliascoelho911.bluetaxi.navigation.transitions.transitionSlideOutHorizontal
import com.bluetaxi.login.presentation.LoginScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginScreen(
    navigationController: BlueTaxiNavigationController,
) {
    composable(
        route = Screen.Login.route,
        enterTransition = {
            transitionSlideInHorizontal()
        },
        popExitTransition = {
            transitionSlideOutHorizontal()
        }
    ) {
        LoginScreen(
            onUserLogIn = {},
            onNavigationBack = navigationController.popBackStackOrNull,
            onClickSignUp = {}
        )
    }
}