package com.github.eliascoelho911.bluetaxi.navigation

sealed class ScreenGroup(val route: String) {
    object Auth : ScreenGroup("auth")
}

sealed class Screen(partialRoute: String, group: ScreenGroup) {
    val route = "${group.route}/$partialRoute"

    object Login : Screen(partialRoute = "login", group = ScreenGroup.Auth)
}