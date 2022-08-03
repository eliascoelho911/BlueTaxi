package com.bluetaxi.login.domain.entities

import com.bluetaxi.commons.email.EmailValidator

data class Credentials(val email: String, val password: String) {
    val emailIsValid: Boolean get() = EmailValidator.isEmail(email)
}
