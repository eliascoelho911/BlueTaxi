package com.github.eliascoelho911.bluetaxi.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.eliascoelho911.bluetaxi.core.commons.EmailValidator
import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButtonState
import kotlinx.coroutines.delay

internal class LoginViewModel : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    suspend fun login(email: String, password: String) {
        if (!EmailValidator.isEmail(email))
            invalidEmailState()

        loadingLogin()
        delay(2000)
        failureOnLoggingInState()
    }

    private fun invalidEmailState() {
        uiState = LoginUiState(emailIsInvalid = true)
    }

    private fun loadingLogin() {
        uiState = LoginUiState(loginButtonState = ProgressButtonState.LOADING)
    }

    private fun successOnLoggingInState() {
        uiState = LoginUiState(loginButtonState = ProgressButtonState.SUCCESS)
    }

    private fun failureOnLoggingInState() {
        uiState = LoginUiState(loginFailed = true)
    }

    fun validateEmail(email: String) {
        if (uiState.emailIsInvalid && EmailValidator.isEmail(email))
            validEmailState()
    }

    private fun validEmailState() {
        uiState = uiState.copy(emailIsInvalid = false)
    }

    fun updateSubmitButtonStateBasedOn(email: String, password: String) {
        uiState = uiState.copy(submitButtonIsEnabled = email.isNotBlank() && password.isNotBlank())
    }

    fun hideLoginFailureDialog() {
        uiState = uiState.copy(loginFailed = false)
    }
}