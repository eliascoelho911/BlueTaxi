package com.github.eliascoelho911.bluetaxi.driver

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class DriverState(
    val navHostController: NavHostController
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberDriverState(
    navHostController: NavHostController = rememberAnimatedNavController(),
) = remember(navHostController) {
    DriverState(navHostController)
}