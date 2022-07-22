package com.github.eliascoelho911.bluetaxi.auth.ui.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.github.eliascoelho911.bluetaxi.auth.R
import com.github.eliascoelho911.bluetaxi.auth.ui.login.LoginScreenTestTags.ErrorDialog
import com.github.eliascoelho911.bluetaxi.commons.test.BaseRobotScreen
import com.github.eliascoelho911.bluetaxi.commons.test.assertHasError
import com.github.eliascoelho911.bluetaxi.commons.test.assertHasNoError

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

    fun assertErrorDialogIsDisplayed() {
        withRule {
            onErrorDialogNode().assertIsDisplayed()
        }
    }

    fun assertErrorDialogIsNotDisplayed() {
        withRule {
            onErrorDialogNode().assertDoesNotExist()
        }
    }

    fun assertLogInButtonIsEnabled() {
        withRule {
            onLogInButtonNode().assertIsEnabled()
        }
    }

    fun assertLogInButtonIsNotEnabled() {
        withRule {
            onLogInButtonNode().assertIsNotEnabled()
        }
    }

    private fun ComposeContentTestRule.onLogInButtonNode() =
        onNode(hasText(getString(R.string.login_submit)).and(hasClickAction()))

    private fun ComposeContentTestRule.onErrorDialogNode() =
        onNodeWithTag(ErrorDialog)

    private fun ComposeContentTestRule.onEmailTextFieldNode() =
        onNodeWithText(getString(R.string.email))

    private fun ComposeContentTestRule.onInvalidEmailAlertNode() =
        onNodeWithText(getString(R.string.invalid_email_error))
}