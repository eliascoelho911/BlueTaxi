package com.bluetaxi.login.presentation

import com.bluetaxi.commons.email.EmailValidator
import com.bluetaxi.designsystem.components.ProgressButtonState
import com.bluetaxi.login.domain.entities.Credentials
import com.bluetaxi.login.domain.usecases.LoginUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.bluetaxi.login.R
import java.lang.IllegalArgumentException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val loginUseCase = mockk<LoginUseCase>()
    private val viewModel = LoginViewModel(loginUseCase)

    @Test
    fun givenInvalidEmail_whenLogIn_shouldEmitInvalidEmailState() = runTest {
        mockEmailValidator(isEmail = false)

        viewModel.logIn(INVALID_CREDENTIALS)

        viewModel.uiState.assertEquals(LoginUiState(emailIsInvalid = true, canLogIn = false))
    }

    @Test
    fun givenCorrectCredentials_whenLogIn_shouldEmitSuccessfullyLoggedInState() = runTest {
        mockEmailValidator(isEmail = true)
        onSuccessfullyLogged()

        viewModel.logIn(VALID_CREDENTIALS)

        viewModel.uiState.assertEquals(LoginUiState(
            loginButtonState = ProgressButtonState.SUCCESS,
            isUserLoggedIn = true))
    }

    @Test
    fun givenIncorrectCredentials_whenLogIn_shouldEmitFailedToLoginState() = runTest {
        mockEmailValidator(isEmail = true)
        onFailedToLogin()

        viewModel.logIn(VALID_CREDENTIALS)

        viewModel.uiState.assertEquals(LoginUiState(errorMessage = R.string.invalid_credentials_error))
    }

    private fun mockEmailValidator(isEmail: Boolean) {
        mockkObject(EmailValidator)

        every { EmailValidator.isEmail(any()) } returns isEmail
    }

    private fun LoginUiState.assertEquals(expected: LoginUiState) {
        assertEquals(expected, this)
    }

    private fun onSuccessfullyLogged() {
        coEvery { loginUseCase(any()) } returns flowOf(String())
    }

    private fun onFailedToLogin() {
        coEvery { loginUseCase(any()) } returns flow {
            throw IllegalArgumentException()
        }
    }
}

private val INVALID_CREDENTIALS = Credentials("email", "pass")
private val VALID_CREDENTIALS = Credentials("email@email.com", "pass")
