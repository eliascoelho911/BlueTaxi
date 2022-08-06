package com.bluetaxi.driver.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class NavigationController(val navHostController: NavHostController) {
    fun navigateToLogin() {
        navHostController.navigate(Screen.Login.route)
    }

    fun navigateToSignUp() {
        navHostController.navigate(Screen.SignUp.route)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberNavigationController(navHostController: NavHostController = rememberAnimatedNavController()) =
    remember {
        NavigationController(navHostController)
    }