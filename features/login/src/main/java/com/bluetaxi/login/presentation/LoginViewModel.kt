package com.bluetaxi.login.presentation

import com.bluetaxi.commons.email.EmailValidator
import com.bluetaxi.core.viewmodel.StateViewModel
import com.bluetaxi.designsystem.components.ProgressButtonState
import com.bluetaxi.login.R
import com.bluetaxi.login.domain.entities.Credentials
import com.bluetaxi.login.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.catch

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : StateViewModel<LoginUiState>(LoginUiState()) {

    suspend fun logIn(credentials: Credentials) {
        if (!credentials.emailIsValid) {
            emailIsInvalid()
            return
        }

        loggingIn()

        loginUseCase(credentials)
            .catch { failedToLogin() }
            .collect {
                successfullyLoggedIn()
            }
    }

    private fun emailIsInvalid() {
        setUiState {
            LoginUiState(emailIsInvalid = true, canLogIn = false)
        }
    }

    private fun loggingIn() {
        setUiState { LoginUiState(loginButtonState = ProgressButtonState.LOADING) }
    }

    private fun successfullyLoggedIn() {
        setUiState { uiState ->
            uiState.copy(loginButtonState = ProgressButtonState.SUCCESS,
                isUserLoggedIn = true)
        }
    }

    private fun failedToLogin() {
        setUiState { LoginUiState(errorMessage = R.string.invalid_credentials_error) }
    }

    fun validateEmailHasBeenCorrected(email: String) {
        if (uiState.emailIsInvalid && EmailValidator.isEmail(email))
            emailIsValid()
    }

    private fun emailIsValid() {
        setUiState { uiState.copy(emailIsInvalid = false) }
    }

    fun validateIfCanLogIn(email: String, password: String) {
        if (!uiState.emailIsInvalid)
            canDontLogin(email, password)
    }

    private fun canDontLogin(email: String, password: String) {
        setUiState { uiState.copy(canLogIn = email.isNotBlank() && password.isNotBlank()) }
    }

    fun errorMessageShown() {
        setUiState { uiState.copy(errorMessage = null) }
    }
}