package com.github.eliascoelho911.bluetaxi.driver

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.github.eliascoelho911.bluetaxi.navigation.BlueTaxiNavigationController
import com.github.eliascoelho911.bluetaxi.navigation.Screen
import com.github.eliascoelho911.bluetaxi.navigation.ScreenGroup
import com.github.eliascoelho911.bluetaxi.navigation.composables.loginScreen
import com.github.eliascoelho911.bluetaxi.navigation.composables.welcomeScreen
import com.github.eliascoelho911.bluetaxi.navigation.rememberBlueTaxiNavigationController
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun DriverRootNavGraph(navigationController: BlueTaxiNavigationController = rememberBlueTaxiNavigationController()) {
    AnimatedNavHost(
        navController = navigationController.navHostController,
        startDestination = ScreenGroup.Auth.route
    ) {
        addAuthNavigation(navigationController)
    }
}

private fun NavGraphBuilder.addAuthNavigation(navigationController: BlueTaxiNavigationController) {
    navigation(startDestination = Screen.Welcome.route, route = ScreenGroup.Auth.route) {
        addWelcomeScreen(navigationController)
        addLoginScreen(navigationController)
    }
}

private fun NavGraphBuilder.addWelcomeScreen(navigationController: BlueTaxiNavigationController) {
    welcomeScreen(
        navigationController = navigationController,
        title = {
            Image(painter = painterResource(id = R.drawable.ic_logo_driver),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.size(151.dp, 107.dp),
                colorFilter = ColorFilter.tint(LocalContentColor.current))
        },
        subtitle = {
            Text(text = stringResource(id = R.string.welcome_subtitle))
        }
    )
}

private fun NavGraphBuilder.addLoginScreen(navigationController: BlueTaxiNavigationController) {
    loginScreen(
        navigationController = navigationController
    )
}