package com.github.eliascoelho911.bluetaxi.auth.ui.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.bluetaxi.auth.di.LoginModule
import com.github.eliascoelho911.bluetaxi.commons.test.BaseComposableTest
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

@RunWith(AndroidJUnit4::class)
class LoginScreenTest : BaseComposableTest() {

    @Before
    fun setup() {
        loadKoinModules(LoginModule)
    }

    @Test
    fun givenInvalidEmailAndAnyPassword_whenTryToLogin_shouldDisplayInvalidEmailAlertAndDisableLogin() =
        composableTest {
            setContent {
                BlueTaxiTheme {
                    LoginScreen(onUserLogIn = {})
                }
            }

            val invalidEmail = "invalidEmail"
            val anyPassword = "anyPassword"

            loginScreenRobot {
                enterEmail(invalidEmail)
                enterPassword(anyPassword)
                clickOnLogInButton()
                assertInvalidEmailAlertIsDisplayed()
                assertLogInButtonIsNotEnabled()
            }
        }
}