package com.bluetaxi.driver.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Welcome: Screen("welcome")
    object SignUp: Screen("signup")
}
