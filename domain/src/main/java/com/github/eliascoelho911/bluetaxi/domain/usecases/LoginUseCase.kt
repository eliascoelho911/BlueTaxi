package com.github.eliascoelho911.bluetaxi.domain.usecases

import kotlinx.coroutines.delay

class LoginUseCase {
    suspend operator fun invoke(email: String, password: String): Boolean {
        delay(2000)
        return email == "123@hotmail.com" && password == "123"
    }
}