package com.bluetaxi.login.presentation

import androidx.annotation.StringRes
import com.bluetaxi.core.viewmodel.UiState
import com.bluetaxi.designsystem.components.ProgressButtonState

data class LoginUiState(
    val emailIsInvalid: Boolean = false,
    val loginButtonState: ProgressButtonState = ProgressButtonState.CONTENT,
    @StringRes val errorMessage: Int? = null,
    val canLogIn: Boolean = false,
    val isUserLoggedIn: Boolean = false
): UiState