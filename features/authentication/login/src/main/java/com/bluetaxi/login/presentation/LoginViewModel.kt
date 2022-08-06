package com.bluetaxi.login.presentation

import androidx.lifecycle.ViewModel
import com.bluetaxi.authentication.domain.entities.Credentials
import com.bluetaxi.authentication.domain.usecases.LoginUseCase
import com.bluetaxi.login.R
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

    fun passwordChanged(newValue: String) = intent {
        reduce {
            state.passwordChanged(newValue)
        }
    }

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

    fun errorShown() = intent {
        reduce { state.errorShown() }
    }

    fun navigationBack() = intent {
        postSideEffect(LoginSideEffect.NavigationBack)
    }

    fun navigateToSignUp() = intent {
        postSideEffect(LoginSideEffect.NavigateToSignUp)
    }
}
