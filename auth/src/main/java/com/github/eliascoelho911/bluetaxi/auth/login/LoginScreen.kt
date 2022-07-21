package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.AlertDialog
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
//todo implementar e testar cenáro de email/senha em branco
@Composable
internal fun LoginScreen(loginViewModel: LoginViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }
    val uiState = loginViewModel.uiState

    LoginScreenContent(
        uiState = uiState,
        email = email,
        password = password,
        onEmailChange = {
            email = it
            if (uiState.emailIsInvalid) {
                loginViewModel.validateEmail(email)
            }
        },
        onPasswordChange = {
            password = it
        },
        onClickSubmit = {
            coroutineScope.launch {
                loginViewModel.login(email, password)
            }
        },
        onDismissLoginFailureDialog = {
            loginViewModel.hideLoginFailureDialog()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenContent(
    uiState: LoginUiState,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickSubmit: () -> Unit,
    onDismissLoginFailureDialog: () -> Unit,
) {
    Scaffold(topBar = { LoginTopAppBar { /*TODO*/ } }) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            Text(modifier = Modifier.padding(horizontal = ScreenPadding),
                text = stringResource(id = R.string.login_subtitle),
                style = MaterialTheme.typography.bodyLarge)

            Spacer(Modifier.height(24.dp))

            EmailTextField(
                email,
                onEmailChange = onEmailChange,
                emailIsInvalid = uiState.emailIsInvalid,
                modifier = Modifier.padding(horizontal = ScreenPadding)
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

            SubmitButton(uiState.loginButtonState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ScreenPadding)
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = onClickSubmit,
                enabled = email.isNotBlank() && password.isNotBlank())

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

        if (uiState.loginFailed) {
            AlertDialog(
                onDismissRequest = onDismissLoginFailureDialog,
                text = { Text(text = stringResource(id = R.string.login_failure)) },
                confirmButton = {
                    TextButton(onClick = onDismissLoginFailureDialog) {
                        Text(text = stringResource(id = android.R.string.ok))
                    }
                })
        }
    }
}

@Composable
private fun SubmitButton(
    loginButtonState: ProgressButtonState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
) {
    ProgressButton(
        onClick = onClick,
        modifier = modifier,
        state = loginButtonState,
        enabled = enabled,
        successContent = {
            Icon(imageVector = Icons.Rounded.Done,
                contentDescription = stringResource(id = R.string.cd_login_success))
        },
    ) {
        Text(text = stringResource(id = R.string.login))
    }
}

@Composable
private fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit,
    emailIsInvalid: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
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
    @Composable get() = ButtonDefaults.textButtonColors(
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )