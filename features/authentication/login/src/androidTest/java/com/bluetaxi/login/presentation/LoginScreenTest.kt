package com.bluetaxi.login.presentation

import androidx.compose.runtime.Composable
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bluetaxi.authentication.domain.usecases.LoginUseCase
import com.bluetaxi.designsystem.theme.BlueTaxiTheme
import com.bluetaxi.login.di.LoginModule
import com.bluetaxi.test.BaseComposableTest
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class LoginScreenTest : BaseComposableTest() {

    private val loginUseCase = mockk<LoginUseCase>()
    private val viewModel = LoginViewModel(loginUseCase)

    @Before
    fun setup() {
        setupKoin()
    }

    @Test
    fun givenInvalidEmailAndAnyPassword_whenTryToLogin_shouldDisplayInvalidEmailAlertAndDisableLogin() =
        composableTest {
            setContent {
                BlueTaxiTheme {
                    LoginScreenImpl()
                }
            }

            loginScreenRobot {
                enterInvalidEmail()
                enterAnyPassword()
                clickOnLogInButton()
                assertInvalidEmailAlertIsDisplayed()
                assertLogInButtonIsNotEnabled()
            }
        }

    @Test
    fun givenInvalidEmailAlertDisplayedAndLoginDisabled_whenEnterValidEmail_shouldHideInvalidEmailAlertAndEnableLogin() =
        composableTest {
            setContent {
                BlueTaxiTheme {
                    LoginScreenImpl()
                }
            }

            loginScreenRobot {
                enterInvalidEmail()
                enterAnyPassword()
                clickOnLogInButton()
                clearEmail()
                enterValidEmail()
                assertInvalidEmailAlertIsNotDisplayed()
                assertLogInButtonIsEnabled()
            }
        }

    @Test
    fun givenInvalidCredentials_whenTryToLogin_shouldDisplayErrorDialog() = composableTest {
        onFailedToLogin()

        setContent {
            BlueTaxiTheme {
                LoginScreenImpl()
            }
        }

        loginScreenRobot {
            enterValidEmail()
            enterAnyPassword()
            clickOnLogInButton()
            assertErrorDialogExists()
        }
    }

    @Test
    fun givenCorrectCredentials_whenTryToLogin_shouldNotDisplayErrorDialogAnd() = composableTest {
        onSuccessfullyLogged()

        setContent {
            BlueTaxiTheme {
                LoginScreenImpl()
            }
        }

        loginScreenRobot {
            enterValidEmail()
            enterAnyPassword()
            clickOnLogInButton()
            assertErrorDialogDoesNotExist()
        }
    }

    private fun setupKoin() {
        val mockModule = module {
            single { loginUseCase }
            viewModel { viewModel }
        }
        loadKoinModules(listOf(LoginModule, mockModule))
    }

    private fun onSuccessfullyLogged() {
        coEvery { loginUseCase(any()) } returns flowOf(String())
    }

    private fun onFailedToLogin() {
        coEvery { loginUseCase(any()) } returns flow {
            throw IllegalArgumentException()
        }
    }

    @Composable
    private fun LoginScreenImpl() {
        LoginScreen(
            onNavigateToHome = {},
            onNavigateToSignUp = {})
    }
}