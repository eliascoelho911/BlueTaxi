package com.bluetaxi.passenger.login.data

import com.bluetaxi.authentication.domain.entities.AccessToken
import com.bluetaxi.authentication.domain.entities.Credentials
import com.bluetaxi.login.domain.errors.LoginError
import com.bluetaxi.login.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.flow

private val MOCK_CREDENTIAL = Credentials("123@hotmail.com", "123")

internal class LoginRepositoryMock : LoginRepository {
    override fun logIn(credentials: Credentials) = flow {
        if (credentials == MOCK_CREDENTIAL)
            emit(AccessToken())
        else
            throw LoginError.InvalidCredentials()
    }
}