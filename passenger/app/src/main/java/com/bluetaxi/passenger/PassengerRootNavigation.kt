package com.bluetaxi.passenger

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
import androidx.navigation.navigation
import com.github.eliascoelho911.bluetaxi.navigation.NavigationController
import com.github.eliascoelho911.bluetaxi.navigation.Screen
import com.github.eliascoelho911.bluetaxi.navigation.ScreenGroup
import com.github.eliascoelho911.bluetaxi.navigation.composables.loginScreen
import com.github.eliascoelho911.bluetaxi.navigation.composables.welcomeScreen
import com.github.eliascoelho911.bluetaxi.navigation.rememberNavigationController
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun PassengerRootNavGraph(navigationController: NavigationController = rememberNavigationController()) {
    AnimatedNavHost(
        navController = navigationController.navHostController,
        startDestination = ScreenGroup.Auth.route
    ) {
        addAuthNavigation(navigationController)
    }
}

private fun NavGraphBuilder.addAuthNavigation(navigationController: NavigationController) {
    navigation(startDestination = Screen.Welcome.route, route = ScreenGroup.Auth.route) {
        addWelcomeScreen(navigationController)
        addLoginScreen(navigationController)
    }
}

private fun NavGraphBuilder.addWelcomeScreen(navigationController: NavigationController) {
    welcomeScreen(
        navigationController,
        title = {
            Image(painter = painterResource(id = R.drawable.ic_logo_passenger),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.size(151.dp, 73.dp))
        },
        subtitle = {
            Text(text = stringResource(id = R.string.welcome_subtitle))
        })
}

private fun NavGraphBuilder.addLoginScreen(navigationController: NavigationController) {
    loginScreen(navigationController)
}
