package com.github.eliascoelho911.bluetaxi.auth.ui.login

import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButtonState

data class LoginUiState(
    val emailIsInvalid: Boolean = false,
    val loginButtonState: ProgressButtonState = ProgressButtonState.CONTENT,
    val loginFailed: Boolean = false,
    val submitButtonIsEnabled: Boolean = false
)