package com.bluetaxi.authentication.domain.usecases

import com.bluetaxi.authentication.domain.entities.AccessToken
import com.bluetaxi.authentication.domain.entities.Credentials
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(credentials: Credentials): Flow<AccessToken>
}