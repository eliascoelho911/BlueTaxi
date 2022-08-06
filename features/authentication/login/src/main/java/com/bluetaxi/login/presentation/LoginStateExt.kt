package com.bluetaxi.login.presentation

import androidx.annotation.StringRes
import com.bluetaxi.commons.email.EmailValidator

internal fun LoginState.invalidEmail() =
    copy(emailIsInvalid = true, loginButtonIsEnabled = false)

internal fun LoginState.loggingIn() = copy(isLoggingIn = true, loginButtonIsEnabled = false)

internal fun LoginState.error(@StringRes message: Int) = copy(errorMessage = message, isLoggingIn = false)

internal fun LoginState.errorShown() = copy(errorMessage = null)

internal fun LoginState.emailChanged(
    newValue: String,
): LoginState {
    val emailIsInvalid = if (emailIsInvalid) !EmailValidator.isEmail(newValue) else false

    val loginButtonIsEnabled = if (!emailIsInvalid)
        newValue.isNotBlank() && password.isNotBlank()
    else false

    return copy(email = newValue,
        emailIsInvalid = emailIsInvalid,
        loginButtonIsEnabled = loginButtonIsEnabled)
}

internal fun LoginState.passwordChanged(
    newValue: String,
) = copy(password = newValue, loginButtonIsEnabled = email.isNotBlank() && newValue.isNotBlank())