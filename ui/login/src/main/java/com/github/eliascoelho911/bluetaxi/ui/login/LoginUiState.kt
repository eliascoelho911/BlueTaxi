package com.github.eliascoelho911.bluetaxi.ui.login

import androidx.annotation.StringRes
import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButtonState

data class LoginUiState(
    val emailIsInvalid: Boolean = false,
    val loginButtonState: ProgressButtonState = ProgressButtonState.CONTENT,
    @StringRes val errorMessage: Int? = null,
    val canLogIn: Boolean = false,
    val isUserLoggedIn: Boolean = false
)