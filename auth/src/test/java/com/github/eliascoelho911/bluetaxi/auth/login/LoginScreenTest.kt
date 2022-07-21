package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.bluetaxi.core.test.BaseComposableTest
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest : BaseComposableTest() {

    @Test
    fun whenEmailIsInvalid_mustDisplayInvalidEmailAlert() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(emailIsInvalid = true))
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
                DefaultLoginScreenContent(LoginUiState(emailIsInvalid = false))
            }
        }

        loginScreenRobot {
            assertInvalidEmailAlertIsNotDisplayed()
        }
    }

    @Test
    fun whenLoginFailed_mustDisplayLoginFailureDialog() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(loginFailed = true))
            }
        }

        loginScreenRobot {
            assertLoginFailureDialogIsDisplayed()
        }
    }

    @Test
    fun whenLoginNotFailed_mustNotDisplayLoginFailureDialog() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(loginFailed = false))
            }
        }

        loginScreenRobot {
            assertLoginFailureDialogIsNotDisplayed()
        }
    }

    @Test
    fun submitButtonIsEnabledTest() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(submitButtonIsEnabled = true))
            }
        }

        loginScreenRobot {
            assertSubmitButtonIsEnabled()
        }
    }

    @Test
    fun submitButtonIsNotEnabledTest() = composableTest {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(submitButtonIsEnabled = false))
            }
        }

        loginScreenRobot {
            assertSubmitButtonIsNotEnabled()
        }
    }
}

@Composable
private fun DefaultLoginScreenContent(uiState: LoginUiState) {
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }

    LoginScreenContent(
        uiState = uiState,
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onClickSubmit = {},
        onDismissLoginFailureDialog = {}
    )
}
