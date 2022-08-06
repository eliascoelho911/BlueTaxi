package com.bluetaxi.login.domain.repositories

import com.bluetaxi.authentication.domain.entities.AccessToken
import com.bluetaxi.authentication.domain.entities.Credentials
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun logIn(credentials: Credentials): Flow<AccessToken>
}