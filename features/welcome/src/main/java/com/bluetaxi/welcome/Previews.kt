package com.bluetaxi.welcome

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bluetaxi.designsystem.theme.BlueTaxiTheme

@Composable
@Preview("Welcome - Dark")
private fun WelcomeScreenDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        WelcomeScreenImpl()
    }
}

@Composable
@Preview("Welcome - Light")
private fun WelcomeScreenLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        WelcomeScreenImpl()
    }
}

@Composable
private fun WelcomeScreenImpl() {
    WelcomeScreen(
        title = {
            Text(text = "BLUE", style = MaterialTheme.typography.displayLarge)
        },
        subtitle = {
            Text(text = "Peça uma viagem quando e onde quiser")
        },
        onClickEnterWithEmail = { },
        onClickSignUp = {})
}