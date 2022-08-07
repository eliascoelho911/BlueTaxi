package com.bluetaxi.passenger.login.domain

import com.bluetaxi.authentication.domain.entities.AccessToken
import com.bluetaxi.authentication.domain.entities.Credentials
import com.bluetaxi.authentication.domain.usecases.LoginUseCase
import com.bluetaxi.login.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow

internal class LoginUseCaseImpl(private val repository: LoginRepository): LoginUseCase {
    override fun invoke(credentials: Credentials): Flow<AccessToken> =
        repository.logIn(credentials)
}