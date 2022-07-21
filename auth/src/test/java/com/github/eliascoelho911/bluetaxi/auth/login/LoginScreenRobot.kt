package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.github.eliascoelho911.bluetaxi.core.test.ComposeTestRobot
import com.github.eliascoelho911.bluetaxi.core.test.assertHasError
import com.github.eliascoelho911.bluetaxi.core.test.assertHasNoError

class LoginScreenRobot(
    composeTestRule: ComposeContentTestRule,
) : ComposeTestRobot(composeTestRule) {

    fun assertInvalidEmailAlertIsDisplayed() = apply {
        withRule {
            onEmailTextFieldNode().assertHasError()
            onInvalidEmailAlertNode().assertIsDisplayed()
        }
    }

    fun assertInvalidEmailAlertIsNotDisplayed() = apply {
        withRule {
            onEmailTextFieldNode().assertHasNoError()
            onInvalidEmailAlertNode().assertDoesNotExist()
        }
    }

    fun assertLoginFailureDialogIsDisplayed() = apply {
        withRule {
            onLoginFailedDialogNode().assertIsDisplayed()
        }
    }

    fun assertLoginFailureDialogIsNotDisplayed() = apply {
        withRule {
            onLoginFailedDialogNode().assertDoesNotExist()
        }
    }

    private fun ComposeContentTestRule.onLoginFailedDialogNode() =
        onNode(hasTestTag(LoginScreenTestTags.LOGIN_FAILED_DIALOG))

    private fun ComposeContentTestRule.onEmailTextFieldNode() =
        onNode(hasText("Email"))

    private fun ComposeContentTestRule.onInvalidEmailAlertNode() =
        onNode(hasTestTag(LoginScreenTestTags.INVALID_EMAIL_ALERT))
}