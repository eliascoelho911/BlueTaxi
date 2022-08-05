package com.bluetaxi.login.presentation

import androidx.annotation.StringRes
import com.bluetaxi.commons.email.EmailValidator

fun LoginState.invalidEmail() =
    copy(emailIsInvalid = true, loginButtonIsEnabled = false)

fun LoginState.loggingIn() = copy(isLoggingIn = true, loginButtonIsEnabled = false)

fun LoginState.error(@StringRes message: Int) = copy(errorMessage = message)

fun LoginState.errorShown() = copy(errorMessage = null)

fun LoginState.emailChanged(
    newValue: String,
): LoginState {
    val emailIsInvalid = !(emailIsInvalid && EmailValidator.isEmail(newValue))

    val loginButtonIsEnabled = if (!emailIsInvalid)
        fieldsAreNotBlank()
    else false

    return copy(email = newValue,
        emailIsInvalid = emailIsInvalid,
        loginButtonIsEnabled = loginButtonIsEnabled)
}

private fun LoginState.fieldsAreNotBlank() = email.isNotBlank() && password.isNotBlank()

fun LoginState.passwordChanged(
    newValue: String,
) = copy(password = newValue, loginButtonIsEnabled = fieldsAreNotBlank())