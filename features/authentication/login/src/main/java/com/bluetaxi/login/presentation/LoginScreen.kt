package com.bluetaxi.login.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bluetaxi.commons.android.compose.hasBackQueue
import com.bluetaxi.designsystem.components.PasswordTextField
import com.bluetaxi.designsystem.components.ProgressButton
import com.bluetaxi.designsystem.components.iconButtons.NavigationBackIcon
import com.bluetaxi.designsystem.util.horizontalScreenPadding
import com.bluetaxi.login.R
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    val viewModel = getViewModel<LoginViewModel>()
    val state by viewModel.container.stateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect {
            handleSideEffect(
                it,
                navHostController,
                onNavigateToHome,
                onNavigateToSignUp
            )
        }
    }

    LoginScreen(
        state = state,
        email = state.email,
        password = state.password,
        navigationIconIsVisible = navHostController.hasBackQueue,
        onEmailChange = { viewModel.emailChanged(it) },
        onPasswordChange = { viewModel.passwordChanged(it) },
        onLoginClick = { viewModel.logIn() },
        onErrorDialogDismiss = { viewModel.errorShown() },
        onNavigationBack = { viewModel.navigationBack() },
        onClickSignUp = { viewModel.navigateToSignUp() }
    )
}

private fun handleSideEffect(
    sideEffect: LoginSideEffect,
    navHostController: NavHostController,
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    when (sideEffect) {
        LoginSideEffect.NavigateToHome -> onNavigateToHome()
        LoginSideEffect.NavigationBack -> navHostController.popBackStack()
        LoginSideEffect.NavigateToSignUp -> onNavigateToSignUp()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginScreen(
    state: LoginState,
    email: String,
    password: String,
    navigationIconIsVisible: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onErrorDialogDismiss: () -> Unit,
    onNavigationBack: () -> Unit,
    onClickSignUp: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarScrollState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { LoginTopAppBar(navigationIconIsVisible, scrollBehavior, onNavigationBack) }
    ) { contentPadding ->
        Column(modifier = Modifier
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())) {
            Text(modifier = Modifier.horizontalScreenPadding(),
                text = stringResource(id = R.string.login_subtitle),
                style = MaterialTheme.typography.bodyLarge)

            Spacer(Modifier.height(24.dp))

            EmailTextField(
                email,
                onEmailChange = onEmailChange,
                emailIsInvalid = state.emailIsInvalid,
                modifier = Modifier.horizontalScreenPadding()
            )

            Spacer(Modifier.height(24.dp))

            PasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScreenPadding(),
                value = password,
                onValueChange = onPasswordChange,
                label = { Text(text = stringResource(id = R.string.password)) }
            )

            SubmitButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScreenPadding()
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = onLoginClick,
                isLoading = state.isLoggingIn,
                enabled = state.loginButtonIsEnabled)

            TextButton(onClick = onClickSignUp,
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

        if (state.errorMessage != null)
            ShowErrorDialog(state.errorMessage, onErrorDialogDismiss)
    }
}

@Composable
private fun LoginTopAppBar(
    navigationIconIsVisible: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigationBack: () -> Unit,
) {
    LargeTopAppBar(
        title = { Text(text = stringResource(id = R.string.login_title)) },
        navigationIcon = { if (navigationIconIsVisible) NavigationBackIcon(onClick = onNavigationBack) },
        scrollBehavior = scrollBehavior)
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
            Text(text = stringResource(id = R.string.invalid_email_error),
                modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun SubmitButton(
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false,
) {
    ProgressButton(
        onClick = onClick,
        modifier = modifier,
        isLoading = isLoading,
        enabled = enabled,
    ) {
        Text(text = stringResource(id = R.string.login_submit))
    }
}

@Composable
private fun ShowErrorDialog(@StringRes messageRes: Int, onDismissErrorDialog: () -> Unit) {
    AlertDialog(
        modifier = Modifier.testTag(LoginScreenTestTags.ErrorDialog),
        onDismissRequest = onDismissErrorDialog,
        text = { Text(text = stringResource(id = messageRes)) },
        confirmButton = {
            TextButton(onClick = onDismissErrorDialog) {
                Text(text = stringResource(id = android.R.string.ok))
            }
        })
}

private val TextButtonSecondaryContentColor
    @Composable get() = ButtonDefaults.textButtonColors(
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

object LoginScreenTestTags {
    const val ErrorDialog = "ErrorDialog"
}