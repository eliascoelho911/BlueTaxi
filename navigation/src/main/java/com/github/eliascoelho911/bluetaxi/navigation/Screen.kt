package com.github.eliascoelho911.bluetaxi.navigation

sealed class ScreenGroup(val route: String) {
    object Auth : ScreenGroup("auth")
}

sealed class Screen(partialRoute: String, group: ScreenGroup? = null) {
    val route = "${group?.let { "$it/" }}$partialRoute"

    object Login : Screen(partialRoute = "login", group = ScreenGroup.Auth)

    object Welcome : Screen(partialRoute = "welcome", group = ScreenGroup.Auth)
}