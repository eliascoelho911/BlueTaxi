package com.bluetaxi.login.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.bluetaxi.commons.email.EmailValidator
import com.bluetaxi.login.R
import com.bluetaxi.login.domain.entities.Credentials
import com.bluetaxi.login.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel(), ContainerHost<LoginState, LoginSideEffect> {

    override val container = container<LoginState, LoginSideEffect>(LoginState())

    fun emailChanged(newValue: String) = intent {
        reduce {
            state.emailChanged(newValue)
        }
    }

    private fun LoginState.emailChanged(
        newValue: String,
    ): LoginState {
        val emailIsInvalid = !(emailIsInvalid && EmailValidator.isEmail(newValue))

        val loginButtonIsEnabled = if (!emailIsInvalid)
            fieldsAreNotBlank()
        else false

        return copy(email = newValue,
            emailIsInvalid = emailIsInvalid,
            loginButtonIsEnabled = loginButtonIsEnabled)
    }

    private fun LoginState.fieldsAreNotBlank() = email.isNotBlank() && password.isNotBlank()

    fun passwordChanged(newValue: String) = intent {
        reduce {
            state.passwordChanged(newValue)
        }
    }

    private fun LoginState.passwordChanged(
        newValue: String,
    ) = copy(password = newValue, loginButtonIsEnabled = fieldsAreNotBlank())

    fun logIn() = intent {
        val credentials = Credentials(state.email, state.password)

        if (!credentials.emailIsValid) {
            reduce { state.invalidEmail() }
        } else {
            reduce { state.loggingIn() }

            loginUseCase(credentials)
                .catch {
                    reduce { state.error(R.string.invalid_credentials_error) }
                }
                .collect {
                    postSideEffect(LoginSideEffect.NavigateToHome)
                }
        }
    }

    private fun LoginState.invalidEmail() =
        copy(emailIsInvalid = true, loginButtonIsEnabled = false)

    private fun LoginState.loggingIn() = copy(isLoggingIn = true, loginButtonIsEnabled = false)

    private fun LoginState.error(@StringRes message: Int) = copy(errorMessage = message)

    fun errorShown() = intent {
        reduce { state.errorShown() }
    }

    private fun LoginState.errorShown() = copy(errorMessage = null)

    fun navigationBack() = intent {
        postSideEffect(LoginSideEffect.NavigationBack)
    }

    fun navigateToSignUp() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }
}
