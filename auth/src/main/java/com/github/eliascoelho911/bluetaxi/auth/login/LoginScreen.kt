package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.eliascoelho911.bluetaxi.auth.R
import com.github.eliascoelho911.bluetaxi.core.ScreenRoute
import com.github.eliascoelho911.bluetaxi.designsystem.components.PasswordTextField
import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButton
import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButtonState
import com.github.eliascoelho911.bluetaxi.designsystem.components.iconButtons.NavigationBackIcon
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme
import kotlinx.coroutines.launch

object LoginScreenRoute : ScreenRoute {
    override val route = "login"
}

//todo testar cenário de erro
//todo testar cenário de sucesso
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginScreen(loginViewModel: LoginViewModel) {
    Scaffold(topBar = { LoginTopAppBar { /*TODO*/ } }) { contentPadding ->
        val coroutineScope = rememberCoroutineScope()
        var email by remember { mutableStateOf(String()) }
        var password by remember { mutableStateOf(String()) }

        LoginScreenContent(
            modifier = Modifier.padding(contentPadding),
            uiState = loginViewModel.uiState,
            email = email,
            password = password,
            onEmailChange = {
                email = it
            },
            onPasswordChange = {
                password = it
            },
            onClickEnter = {
                coroutineScope.launch {
                    loginViewModel.login(Credentials(email, password))
                }
            }
        )
    }
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    email: String = String(),
    password: String = String(),
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onClickEnter: () -> Unit = {},
) {
    Column(modifier = modifier) {
        Text(modifier = Modifier.padding(horizontal = ScreenPadding),
            text = stringResource(id = R.string.login_subtitle),
            style = MaterialTheme.typography.bodyLarge)

        Spacer(Modifier.height(24.dp))

        EmailTextField(
            email,
            onEmailChange = onEmailChange,
            emailIsInvalid = uiState.showsInvalidEmailError
        )

        Spacer(Modifier.height(24.dp))

        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ScreenPadding),
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = stringResource(id = R.string.password)) }
        )

        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = ScreenPadding),
            colors = TextButtonSecondaryContentColor
        ) {
            Text(text = stringResource(id = R.string.forgot_password))
        }

        EnterButton(uiState.loggingInState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ScreenPadding)
                .align(Alignment.CenterHorizontally),
            onClick = onClickEnter)

        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = TextButtonSecondaryContentColor
        ) {
            Text(text = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_account))
                append(" ")

                withStyle(style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )) {
                    append(stringResource(id = R.string.registration))
                }
            })
        }
    }
}

@Composable
private fun EnterButton(
    loggingInState: LoggingInState?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val progressButtonState = when (loggingInState) {
        LoggingInState.LOADING -> ProgressButtonState.LOADING
        LoggingInState.SUCCESS -> ProgressButtonState.SUCCESS
        LoggingInState.FAILURE -> ProgressButtonState.FAILURE
        else -> ProgressButtonState.CONTENT
    }

    ProgressButton(
        onClick = onClick,
        modifier = modifier,
        state = progressButtonState,
        successContent = {
            Icon(imageVector = Icons.Rounded.Done,
                contentDescription = stringResource(id = R.string.cd_login_success))
        },
        failureContent = {
            Icon(imageVector = Icons.Rounded.Close,
                contentDescription = stringResource(id = R.string.cd_login_failure))
        }
    ) {
        Text(text = stringResource(id = R.string.login))
    }
}

@Composable
private fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    emailIsInvalid: Boolean,
) {
    Column(modifier = Modifier.padding(horizontal = ScreenPadding)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = onEmailChange,
            label = { Text(text = stringResource(id = R.string.email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailIsInvalid)

        if (emailIsInvalid)
            Text(text = stringResource(id = R.string.email_error),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun LoginTopAppBar(onNavigationBack: () -> Unit) {
    LargeTopAppBar(
        title = { Text(text = stringResource(id = R.string.login_title)) },
        navigationIcon = { NavigationBackIcon(onClick = onNavigationBack) })
}


private val ScreenPadding = 16.dp

private val TextButtonSecondaryContentColor
    @Composable get() =
        ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurfaceVariant)

@Preview("Login - Dark")
@Composable
private fun LoginScreenDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        LoginScreen(LoginViewModel())
    }
}

@Preview("Email invalid - Dark")
@Composable
private fun EmailInvalidDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                uiState = LoginUiState(showsInvalidEmailError = true),
            )
        }
    }
}

@Preview("Loading logging in - Dark")
@Composable
private fun LoadingLoggingInDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                uiState = LoginUiState(loggingInState = LoggingInState.LOADING),
            )
        }
    }
}

@Preview("Success on logging in - Dark")
@Composable
private fun SuccessLoggingInDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                uiState = LoginUiState(loggingInState = LoggingInState.SUCCESS),
            )
        }
    }
}

@Preview("Failure on logging in - Dark")
@Composable
private fun FailureLoggingInDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                uiState = LoginUiState(loggingInState = LoggingInState.FAILURE),
            )
        }
    }
}

@Preview("Login - Light")
@Composable
private fun LoginScreenLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        LoginScreen(LoginViewModel())
    }
}

@Preview("Email invalid - Light", showBackground = true)
@Composable
private fun EmailInvalidLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                uiState = LoginUiState(showsInvalidEmailError = true),
            )
        }
    }
}

@Preview("Loading logging in - Light", showBackground = true)
@Composable
private fun LoadingLoggingInLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                uiState = LoginUiState(loggingInState = LoggingInState.LOADING),
            )
        }
    }
}

@Preview("Success on logging in - Light", showBackground = true)
@Composable
private fun SuccessLoggingInLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                uiState = LoginUiState(loggingInState = LoggingInState.SUCCESS),
            )
        }
    }
}

@Preview("Failure on logging in - Light", showBackground = true)
@Composable
private fun FailureLoggingInLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreenContent(
                uiState = LoginUiState(loggingInState = LoggingInState.FAILURE),
            )
        }
    }
}