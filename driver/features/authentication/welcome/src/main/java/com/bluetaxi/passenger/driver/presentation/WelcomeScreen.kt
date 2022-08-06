package com.bluetaxi.passenger.driver.presentation

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
import com.bluetaxi.driver.navigation.NavigationController
import com.bluetaxi.driver.welcome.R

@Composable
fun WelcomeScreen(navigationController: NavigationController) {
    com.bluetaxi.welcome.WelcomeScreen(
        title = {
            Image(painter = painterResource(id = R.drawable.ic_logo_driver),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.size(151.dp, 107.dp),
                colorFilter = ColorFilter.tint(LocalContentColor.current))
        },
        subtitle = { Text(text = stringResource(id = R.string.welcome_subtitle)) },
        onNavigateToLogin = { navigationController.navigateToLogin() },
        onNavigateToSignUp = { navigationController.navigateToSignUp() })
}