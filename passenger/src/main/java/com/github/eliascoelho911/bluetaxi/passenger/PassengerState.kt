package com.github.eliascoelho911.bluetaxi.passenger

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class PassengerState(
    val navHostController: NavHostController
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberPassengerState(
    navHostController: NavHostController = rememberAnimatedNavController(),
) = remember(navHostController) {
    PassengerState(navHostController)
}