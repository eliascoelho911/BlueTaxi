package com.github.eliascoelho911.bluetaxi.auth.login

import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButtonState

data class LoginUiState(
    val invalidEmailErrorIsVisible: Boolean = false,
    val loginButtonState: ProgressButtonState = ProgressButtonState.CONTENT,
    val loginFailureDialogIsVisible: Boolean = false
)