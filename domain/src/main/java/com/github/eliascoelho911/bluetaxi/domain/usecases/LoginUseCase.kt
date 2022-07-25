package com.github.eliascoelho911.bluetaxi.domain.usecases

class LoginUseCase {
    operator fun invoke(email: String, password: String) =
        email == "123@hotmail.com" && password == "123"
}