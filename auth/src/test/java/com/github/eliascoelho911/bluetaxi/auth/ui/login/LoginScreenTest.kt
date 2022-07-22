package com.github.eliascoelho911.bluetaxi.auth.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.bluetaxi.auth.R
import com.github.eliascoelho911.bluetaxi.commons.test.BaseComposableTest
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest : BaseComposableTest() {

    @Test
    fun whenEmailIsInvalid_mustDisplayInvalidEmailAlert() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreen(LoginUiState(emailIsInvalid = true))
            }
        }

        loginScreenRobot {
            assertInvalidEmailAlertIsDisplayed()
        }
    }

    @Test
    fun whenEmailIsValid_mustNotDisplayInvalidEmailAlert() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreen(LoginUiState(emailIsInvalid = false))
            }
        }

        loginScreenRobot {
            assertInvalidEmailAlertIsNotDisplayed()
        }
    }

    @Test
    fun whenFailedToLogin_mustDisplayErrorDialog() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreen(LoginUiState(errorMessage = R.string.invalid_credentials_error))
            }
        }

        loginScreenRobot {
            assertErrorDialogIsDisplayed()
        }
    }

    @Test
    fun whenSuccessfullyLoggedIn_mustNotDisplayErrorDialog() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreen(LoginUiState(errorMessage = null))
            }
        }

        loginScreenRobot {
            assertErrorDialogIsNotDisplayed()
        }
    }

    @Test
    fun logInButtonIsEnabledTest() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreen(LoginUiState(canLogIn = true))
            }
        }

        loginScreenRobot {
            assertLogInButtonIsEnabled()
        }
    }

    @Test
    fun logInButtonIsNotEnabledTest() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreen(LoginUiState(canLogIn = false))
            }
        }

        loginScreenRobot {
            assertLogInButtonIsNotEnabled()
        }
    }
}

@Composable
private fun DefaultLoginScreen(uiState: LoginUiState) {
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }

    LoginScreen(
        uiState = uiState,
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onClickSubmit = {},
        onDismissErrorDialog = {}
    )
}
