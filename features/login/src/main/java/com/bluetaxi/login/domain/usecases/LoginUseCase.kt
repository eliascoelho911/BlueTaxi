package com.bluetaxi.login.domain.usecases

import com.bluetaxi.login.domain.entities.AccessToken
import com.bluetaxi.login.domain.entities.Credentials
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(credentials: Credentials): Flow<AccessToken>
}