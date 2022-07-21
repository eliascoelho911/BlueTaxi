package com.github.eliascoelho911.bluetaxi.commons

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class BlueTaxiState(
    val navHostController: NavHostController
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberBlueTaxiState(
    navHostController: NavHostController = rememberAnimatedNavController(),
) = remember(navHostController) {
    BlueTaxiState(navHostController)
}