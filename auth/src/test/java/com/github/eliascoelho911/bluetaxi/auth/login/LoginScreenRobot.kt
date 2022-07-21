package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.github.eliascoelho911.bluetaxi.auth.R
import com.github.eliascoelho911.bluetaxi.auth.login.LoginScreenTestTags.LoginFailedDialog
import com.github.eliascoelho911.bluetaxi.core.test.BaseRobotScreen
import com.github.eliascoelho911.bluetaxi.core.test.assertHasError
import com.github.eliascoelho911.bluetaxi.core.test.assertHasNoError

internal fun LoginScreenTest.loginScreenRobot(
    block: LoginScreenRobot.() -> Unit,
) {
    LoginScreenRobot(this).apply(block)
}

internal class LoginScreenRobot(
    loginScreenTest: LoginScreenTest,
) : BaseRobotScreen(loginScreenTest.composeTestRule) {

    fun assertInvalidEmailAlertIsDisplayed() {
        withRule {
            onEmailTextFieldNode().assertHasError()
            onInvalidEmailAlertNode().assertIsDisplayed()
        }
    }

    fun assertInvalidEmailAlertIsNotDisplayed() {
        withRule {
            onEmailTextFieldNode().assertHasNoError()
            onInvalidEmailAlertNode().assertDoesNotExist()
        }
    }

    fun assertLoginFailureDialogIsDisplayed() {
        withRule {
            onLoginFailedDialogNode().assertIsDisplayed()
        }
    }

    fun assertLoginFailureDialogIsNotDisplayed() {
        withRule {
            onLoginFailedDialogNode().assertDoesNotExist()
        }
    }

    fun assertSubmitButtonIsEnabled() {
        withRule {
            onSubmitButtonNode().assertIsEnabled()
        }
    }

    fun assertSubmitButtonIsNotEnabled() {
        withRule {
            onSubmitButtonNode().assertIsNotEnabled()
        }
    }

    private fun ComposeContentTestRule.onSubmitButtonNode() =
        onNode(hasText(getString(R.string.login_submit)).and(hasClickAction()))

    private fun ComposeContentTestRule.onLoginFailedDialogNode() =
        onNodeWithTag(LoginFailedDialog)

    private fun ComposeContentTestRule.onEmailTextFieldNode() =
        onNodeWithText(getString(R.string.email))

    private fun ComposeContentTestRule.onInvalidEmailAlertNode() =
        onNodeWithText(getString(R.string.invalid_email_error))
}