package com.github.eliascoelho911.bluetaxi.commons.ui.login

import com.github.eliascoelho911.bluetaxi.commons.EmailValidator
import com.github.eliascoelho911.bluetaxi.designsystem.components.ProgressButtonState
import com.github.eliascoelho911.bluetaxi.domain.usecases.LoginUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val loginUseCase = mockk<LoginUseCase>()
    private val viewModel = LoginViewModel(loginUseCase)

    @Test
    fun givenInvalidEmail_whenLogIn_shouldEmitInvalidEmailState() = runTest {
        mockEmailValidator(isEmail = false)

        viewModel.logIn(INVALID_EMAIL, VALID_PASSWORD)

        viewModel.uiState.assertEquals(LoginUiState(emailIsInvalid = true, canLogIn = false))
    }

    @Test
    fun givenCorrectCredentials_whenLogIn_shouldEmitSuccessfullyLoggedInState() = runTest {
        mockEmailValidator(isEmail = true)
        mockLoginUseCase(successfullyLogged = true)

        viewModel.logIn(VALID_EMAIL, VALID_PASSWORD)

        viewModel.uiState.assertEquals(LoginUiState(
            loginButtonState = ProgressButtonState.SUCCESS,
            isUserLoggedIn = true))
    }

    @Test
    fun givenIncorrectCredentials_whenLogIn_shouldEmitFailedToLoginState() = runTest {
        mockEmailValidator(isEmail = true)
        mockLoginUseCase(successfullyLogged = false)

        viewModel.logIn(VALID_EMAIL, VALID_PASSWORD)

        viewModel.uiState.assertEquals(LoginUiState(errorMessage = R.string.invalid_credentials_error))
    }

    private fun mockEmailValidator(isEmail: Boolean) {
        mockkObject(EmailValidator)

        every { EmailValidator.isEmail(any()) } returns isEmail
    }

    private fun LoginUiState.assertEquals(expected: LoginUiState) {
        assertEquals(expected, this)
    }

    private fun mockLoginUseCase(successfullyLogged: Boolean) {
        coEvery { loginUseCase(any(), any()) } returns successfullyLogged
    }
}


private const val VALID_PASSWORD = "password"
private const val VALID_EMAIL = "email@email.com"
private const val INVALID_EMAIL = "email"
