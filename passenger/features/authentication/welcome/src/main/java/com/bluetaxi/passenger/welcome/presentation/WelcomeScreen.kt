package com.bluetaxi.passenger.welcome.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bluetaxi.passenger.navigation.NavigationController
import com.bluetaxi.passenger.welcome.R

@Composable
fun WelcomeScreen(navigationController: NavigationController) {
    com.bluetaxi.welcome.WelcomeScreen(
        title = {
            Image(painter = painterResource(id = R.drawable.ic_logo_passenger),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier.size(151.dp, 73.dp))
        },
        subtitle = { Text(text = stringResource(id = R.string.welcome_subtitle)) },
        onNavigateToLogin = { navigationController.navigateToLogin() },
        onNavigateToSignUp = { navigationController.navigateToSignUp() })
}