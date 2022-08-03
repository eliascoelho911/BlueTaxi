package com.github.eliascoelho911.bluetaxi.navigation.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.github.eliascoelho911.bluetaxi.navigation.BlueTaxiNavigationController
import com.github.eliascoelho911.bluetaxi.navigation.Screen
import com.bluetaxi.welcome.WelcomeScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.welcomeScreen(
    navigationController: BlueTaxiNavigationController,
    title: @Composable ColumnScope.() -> Unit,
    subtitle: @Composable ColumnScope.() -> Unit,
    onClickEnterWithEmail: () -> Unit = { navigationController.navigateToLoginScreen() }
) {
    composable(
        route = Screen.Welcome.route
    ) {
        WelcomeScreen(
            title = title,
            subtitle = subtitle,
            onClickEnterWithEmail = onClickEnterWithEmail,
            onClickSignUp = {}
        )
    }
}