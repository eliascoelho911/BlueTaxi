package com.bluetaxi.passenger.login.domain

import com.bluetaxi.authentication.domain.entities.AccessToken
import com.bluetaxi.authentication.domain.entities.Credentials
import com.bluetaxi.authentication.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.Flow

class LoginUseCaseImpl: LoginUseCase {
    override fun invoke(credentials: Credentials): Flow<AccessToken> {
        TODO("Not yet implemented")
    }
}