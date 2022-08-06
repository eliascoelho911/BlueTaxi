package com.bluetaxi.login.domain.errors

sealed class LoginError(cause: Throwable? = null) : RuntimeException(cause) {
    class InvalidCredentials(cause: Throwable? = null): LoginError(cause)
}
