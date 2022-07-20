package com.github.eliascoelho911.bluetaxi.auth.login

data class LoginUiState(
    val showsInvalidEmailError: Boolean = false,
    val loggingInState: LoggingInState? = null
)

enum class LoggingInState {
    LOADING, SUCCESS, FAILURE
}