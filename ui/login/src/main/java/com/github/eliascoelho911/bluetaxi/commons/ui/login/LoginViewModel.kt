package com.github.eliascoelho911.bluetaxi.commons.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.eliascoelho911.bluetaxi.commons.EmailValidator
import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButtonState
import com.github.eliascoelho911.bluetaxi.domain.usecases.LoginUseCase
import kotlinx.coroutines.delay

internal class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    suspend fun logIn(email: String, password: String) {
        if (!EmailValidator.isEmail(email)) {
            invalidEmail()
            return
        }

        loggingIn()

        delay(2000)

        val isSuccessfullyLoggedIn = loginUseCase(email, password)
        if (isSuccessfullyLoggedIn) successfullyLoggedIn() else failedToLogin()
    }

    private fun invalidEmail() {
        uiState = LoginUiState(emailIsInvalid = true, canLogIn = false)
    }

    private fun loggingIn() {
        uiState = LoginUiState(loginButtonState = ProgressButtonState.LOADING)
    }

    private fun successfullyLoggedIn() {
        uiState = uiState.copy(loginButtonState = ProgressButtonState.SUCCESS, isUserLoggedIn = true)
    }

    private fun failedToLogin() {
        uiState = LoginUiState(errorMessage = R.string.invalid_credentials_error)
    }

    fun validateEmailHasBeenCorrected(email: String) {
        if (uiState.emailIsInvalid && EmailValidator.isEmail(email))
            validEmail()
    }

    private fun validEmail() {
        uiState = uiState.copy(emailIsInvalid = false)
    }

    fun validateIfCanLogIn(email: String, password: String) {
        if (!uiState.emailIsInvalid)
            uiState = uiState.copy(canLogIn = email.isNotBlank() && password.isNotBlank())
    }

    fun errorMessageShown() {
        uiState = uiState.copy(errorMessage = null)
    }
}