package com.github.eliascoelho911.bluetaxi.navigation.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.github.eliascoelho911.bluetaxi.navigation.BlueTaxiNavigationController
import com.github.eliascoelho911.bluetaxi.navigation.Screen
import com.github.eliascoelho911.bluetaxi.navigation.rememberBlueTaxiNavigationController
import com.github.eliascoelho911.bluetaxi.ui.welcome.WelcomeScreen
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