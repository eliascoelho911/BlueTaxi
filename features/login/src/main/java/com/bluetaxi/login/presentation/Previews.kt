package com.bluetaxi.login.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.bluetaxi.designsystem.theme.BlueTaxiTheme
import com.bluetaxi.login.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview("Email invalid - Dark")
@Composable
private fun EmailInvalidDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        LoginScreenPreview(targetStateOnSubmit = LoginState(emailIsInvalid = true))
    }
}

@Preview("Failure on logging in - Dark")
@Composable
private fun FailureLoggingInDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        LoginScreenPreview(targetStateOnSubmit = LoginState(
            isLoggingIn = false,
            errorMessage = R.string.invalid_credentials_error))
    }
}

@Preview("Email invalid - Light", showBackground = true)
@Composable
private fun EmailInvalidLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        LoginScreenPreview(targetStateOnSubmit = LoginState(emailIsInvalid = true))
    }
}

@Preview("Failure on logging in - Light", showBackground = true)
@Composable
private fun FailureLoggingInLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        LoginScreenPreview(targetStateOnSubmit = LoginState(
            isLoggingIn = false,
            errorMessage = R.string.invalid_credentials_error))
    }
}


@Composable
private fun LoginScreenPreview(targetStateOnSubmit: LoginState) {
    var state by remember { mutableStateOf(LoginState()) }
    val coroutineScope = rememberCoroutineScope()
    LoginScreenPreview(
        state = state,
        onLoginClick = {
            coroutineScope.launch {
                state = LoginState(isLoggingIn = true)
                delay(2000)
                state = targetStateOnSubmit
            }
        },
        onErrorDialogDismiss = {
            state = state.copy(errorMessage = R.string.invalid_credentials_error)
        }
    )
}

@Composable
private fun LoginScreenPreview(
    state: LoginState,
    onLoginClick: () -> Unit,
    onErrorDialogDismiss: () -> Unit,
) {
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }

    LoginScreen(state = state,
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onLoginClick = onLoginClick,
        onErrorDialogDismiss = onErrorDialogDismiss,
        navigationIconIsVisible = false,
        onNavigationBack = {},
        onClickSignUp = {})
}