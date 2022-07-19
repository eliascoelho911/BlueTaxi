package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.github.eliascoelho911.bluetaxi.designsystem.components.iconButtons.NavigationBackIcon
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme

object LoginScreenRoute : ScreenRoute {
    override val route = "login"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(topBar = { LoginTopAppBar { /*TODO*/ } }) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            Text(modifier = Modifier.padding(horizontal = ScreenPadding),
                text = stringResource(id = R.string.login_subtitle),
                style = MaterialTheme.typography.bodyLarge)

            Spacer(Modifier.height(24.dp))

            TextField(
                modifier = TextFieldModifier,
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(id = R.string.email)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))

            Spacer(Modifier.height(24.dp))

            PasswordTextField(
                modifier = TextFieldModifier,
                value = password,
                onValueChange = { password = it },
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

            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = ScreenPadding)
            ) {
                Text(text = stringResource(id = R.string.login))
            }

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
}

@Composable
private fun LoginTopAppBar(onNavigationBack: () -> Unit) {
    LargeTopAppBar(
        title = { Text(text = stringResource(id = R.string.login_title)) },
        navigationIcon = { NavigationBackIcon(onClick = onNavigationBack) })
}

@Preview("dark")
@Composable
private fun LoginScreenDarkPreview() {
    BlueTaxiTheme(useDarkTheme = true) {
        LoginScreen()
    }
}

@Preview("light")
@Composable
private fun LoginScreenLightPreview() {
    BlueTaxiTheme(useDarkTheme = false) {
        LoginScreen()
    }
}

private val ScreenPadding = 16.dp

private val TextFieldModifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = ScreenPadding)

private val TextButtonSecondaryContentColor @Composable get() =
    ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.secondary)