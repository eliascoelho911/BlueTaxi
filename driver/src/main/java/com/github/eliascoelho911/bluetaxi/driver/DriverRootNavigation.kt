package com.github.eliascoelho911.bluetaxi.driver

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.github.eliascoelho911.bluetaxi.commons.popBackStackOrNull
import com.github.eliascoelho911.bluetaxi.designsystem.transitions.transitionSlideInHorizontal
import com.github.eliascoelho911.bluetaxi.designsystem.transitions.transitionSlideOutHorizontal
import com.github.eliascoelho911.bluetaxi.navigation.Screen
import com.github.eliascoelho911.bluetaxi.navigation.ScreenGroup
import com.github.eliascoelho911.bluetaxi.ui.login.LoginScreen
import com.github.eliascoelho911.bluetaxi.ui.welcome.WelcomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun PassengerRootNavGraph(driverState: DriverState = rememberDriverState()) {
    AnimatedNavHost(
        navController = driverState.navHostController,
        startDestination = ScreenGroup.Auth.route
    ) {
        addAuthNavigation(driverState.navHostController)
    }
}

private fun NavGraphBuilder.addAuthNavigation(navHostController: NavHostController) {
    navigation(startDestination = Screen.Welcome.route, route = ScreenGroup.Auth.route) {
        addWelcomeScreen(navHostController)
        addLoginScreen(navHostController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addWelcomeScreen(navHostController: NavHostController) {
    composable(
        route = Screen.Welcome.route
    ) {
        WelcomeScreen(
            title = {
                Image(painter = painterResource(id = R.drawable.ic_logo_driver),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier.size(151.dp, 107.dp))
            },
            subtitle = {
                Text(text = stringResource(id = R.string.welcome_subtitle))
            },
            onClickEnterWithEmail = {
                navHostController.navigate(Screen.Login.route)
            },
            onClickSignUp = {}
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.addLoginScreen(navHostController: NavHostController) {
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
            onNavigationBack = navHostController.popBackStackOrNull,
            onClickSignUp = {}
        )
    }
}