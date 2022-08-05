package com.bluetaxi.login.presentation

import com.bluetaxi.authentication.domain.usecases.LoginUseCase
import com.bluetaxi.commons.email.EmailValidator
import com.bluetaxi.login.R
import com.bluetaxi.test.runOrbitTest
import com.bluetaxi.test.state
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val loginUseCase = mockk<LoginUseCase>()
    private val viewModel = LoginViewModel(loginUseCase)

    @Test
    fun givenInvalidEmail_whenLogIn_shouldEmitInvalidEmailState() = runTest {
        val initialState = LoginState()

        mockEmailValidator(isEmail = false)

        viewModel.runOrbitTest(initialState) {
            testIntent { viewModel.logIn() }

            assert(initialState) {
                state { invalidEmail() }
            }
        }
    }

    @Test
    fun givenValidEmailAndSuccessfulLogin_whenLogIn_shouldEmitSuccessfullyLoggedInState() =
        runTest {
            val initialState = LoginState(email = VALID_EMAIL, password = VALID_PASSWORD)

            mockEmailValidator(isEmail = true)
            onSuccessfullyLogged()

            viewModel.runOrbitTest(initialState) {
                testIntent { viewModel.logIn() }

                assert(initialState) {
                    state { loggingIn() }
                    postedSideEffects(LoginSideEffect.NavigateToHome)
                }
            }
        }

    @Test
    fun givenValidEmailAndUnsuccessfulLogin_whenLogIn_shouldEmitFailedToLoginState() = runTest {
        mockEmailValidator(isEmail = true)
        onFailedToLogin()

        val initialState = LoginState(email = VALID_EMAIL, password = VALID_PASSWORD)

        viewModel.runOrbitTest(initialState) {
            testIntent { viewModel.logIn() }

            assert(initialState) {
                states(
                    { loggingIn() },
                    { error(R.string.invalid_credentials_error) }
                )
            }
        }
    }

    private fun mockEmailValidator(isEmail: Boolean) {
        mockkObject(EmailValidator)

        every { EmailValidator.isEmail(any()) } returns isEmail
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

private const val VALID_EMAIL = "email@email.com"
private const val VALID_PASSWORD = "pass"
