package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButtonState
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview("Email invalid - Dark")
@Composable
private fun EmailInvalidDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        LoginScreenContentPreview(targetUiStateOnSubmit = LoginUiState(emailIsInvalid = true))
    }
}

@Preview("Success on logging in - Dark")
@Composable
private fun SuccessLoggingInDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        LoginScreenContentPreview(targetUiStateOnSubmit = LoginUiState(loginButtonState = ProgressButtonState.SUCCESS))
    }
}

@Preview("Failure on logging in - Dark")
@Composable
private fun FailureLoggingInDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        LoginScreenContentPreview(targetUiStateOnSubmit = LoginUiState(
            loginButtonState = ProgressButtonState.CONTENT,
            loginFailed = true))
    }
}

@Preview("Email invalid - Light", showBackground = true)
@Composable
private fun EmailInvalidLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        LoginScreenContentPreview(targetUiStateOnSubmit = LoginUiState(emailIsInvalid = true))
    }
}

@Preview("Success on logging in - Light", showBackground = true)
@Composable
private fun SuccessLoggingInLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        LoginScreenContentPreview(targetUiStateOnSubmit = LoginUiState(loginButtonState = ProgressButtonState.SUCCESS))
    }
}

@Preview("Failure on logging in - Light", showBackground = true)
@Composable
private fun FailureLoggingInLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        LoginScreenContentPreview(targetUiStateOnSubmit = LoginUiState(
            loginButtonState = ProgressButtonState.CONTENT,
            loginFailed = true))
    }
}


@Composable
private fun LoginScreenContentPreview(targetUiStateOnSubmit: LoginUiState) {
    var uiState by remember { mutableStateOf(LoginUiState()) }
    val coroutineScope = rememberCoroutineScope()
    LoginScreenContentPreview(
        uiState = uiState,
        onClickSubmit = {
            coroutineScope.launch {
                uiState = LoginUiState(loginButtonState = ProgressButtonState.LOADING)
                delay(2000)
                uiState = targetUiStateOnSubmit
            }
        },
        onDismissLoginFailureDialog = {
            uiState = uiState.copy(loginFailed = false)
        }
    )
}

@Composable
private fun LoginScreenContentPreview(
    uiState: LoginUiState,
    onClickSubmit: () -> Unit,
    onDismissLoginFailureDialog: () -> Unit,
) {
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }

    LoginScreenContent(uiState = uiState,
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onClickSubmit = onClickSubmit,
        onDismissLoginFailureDialog = onDismissLoginFailureDialog)
}