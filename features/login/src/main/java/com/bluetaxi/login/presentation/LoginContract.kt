package com.bluetaxi.login.presentation

import androidx.annotation.StringRes

data class LoginState(
    val email: String = String(),
    val password: String = String(),
    val emailIsInvalid: Boolean = false,
    val isLoggingIn: Boolean = false,
    val loginButtonIsEnabled: Boolean = false,
    @StringRes val errorMessage: Int? = null
)

sealed class LoginSideEffect {
    object NavigationBack : LoginSideEffect()
    object NavigateToHome : LoginSideEffect()
    object NavigateToSignUp : LoginSideEffect()
}