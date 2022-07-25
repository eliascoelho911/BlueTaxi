package com.github.eliascoelho911.bluetaxi.commons.ui.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.bluetaxi.commons.test.BaseComposableTest
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme
import com.github.eliascoelho911.bluetaxi.domain.di.DomainModule
import com.github.eliascoelho911.bluetaxi.domain.usecases.LoginUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
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
                    LoginScreen(onUserLogIn = {})
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
                    LoginScreen(onUserLogIn = {})
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
        mockLoginUseCase(result = false)

        setContent {
            BlueTaxiTheme {
                LoginScreen(onUserLogIn = {})
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
        mockLoginUseCase(result = true)
        val onUserLogIn = mockk<() -> Unit>(relaxed = true)

        setContent {
            BlueTaxiTheme {
                LoginScreen(onUserLogIn = onUserLogIn, delayToFinishLogin = 0)
            }
        }

        loginScreenRobot {
            enterValidEmail()
            enterAnyPassword()
            clickOnLogInButton()
            assertErrorDialogDoesNotExist()
            verify { onUserLogIn() }
        }
    }

    private fun setupKoin() {
        val mockModule = module {
            single { loginUseCase }
            viewModel { viewModel }
        }
        loadKoinModules(listOf(DomainModule, LoginUiModule, mockModule))
    }

    private fun mockLoginUseCase(result: Boolean) {
        coEvery { loginUseCase(any(), any()) } returns result
    }
}