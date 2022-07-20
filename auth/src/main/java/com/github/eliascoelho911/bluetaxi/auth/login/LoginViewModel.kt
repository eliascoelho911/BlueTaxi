package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

private const val DELAY_TO_SHOW_CONTENT_ON_FAILURE = 2000L

internal class LoginViewModel : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    suspend fun login(credentials: Credentials) {
        loggingInState()
        delay(2000)
        failureOnLoggingInState()
    }

    private fun successOnLoggingInState() {
        uiState = LoginUiState(loggingInState = LoggingInState.SUCCESS)
    }

    private suspend fun failureOnLoggingInState() {
        uiState = LoginUiState(loggingInState = LoggingInState.FAILURE)
        delay(DELAY_TO_SHOW_CONTENT_ON_FAILURE)
        uiState = LoginUiState()
    }

    private fun loggingInState() {
        uiState = LoginUiState(loggingInState = LoggingInState.LOADING)
    }
}