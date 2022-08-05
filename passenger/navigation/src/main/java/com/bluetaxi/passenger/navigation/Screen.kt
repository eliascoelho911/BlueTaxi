package com.bluetaxi.passenger.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object SignUp: Screen("signup")
}
