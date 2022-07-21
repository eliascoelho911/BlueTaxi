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
    private val robot = LoginScreenRobot(composeTestRule)

    @Test
    fun whenEmailIsInvalid_mustDisplayInvalidEmailAlert() = test {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(emailIsInvalid = true))
            }
        }

        robot.assertInvalidEmailAlertIsDisplayed()
    }

    @Test
    fun whenEmailIsValid_mustNotDisplayInvalidEmailAlert() = test {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(emailIsInvalid = false))
            }
        }

        robot.assertInvalidEmailAlertIsNotDisplayed()
    }

    @Test
    fun whenLoginFailed_mustDisplayLoginFailureDialog() = test {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(loginFailed = true))
            }
        }

        robot.assertLoginFailureDialogIsDisplayed()
    }

    @Test
    fun whenLoginNotFailed_mustNotDisplayLoginFailureDialog() = test {
        setContent {
            BlueTaxiTheme {
                DefaultLoginScreenContent(LoginUiState(loginFailed = false))
            }
        }

        robot.assertLoginFailureDialogIsNotDisplayed()
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
