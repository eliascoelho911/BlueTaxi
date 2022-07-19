package com.github.eliascoelho911.bluetaxi.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class BlueTaxiState(
    val navHostController: NavHostController,
)

@Composable
fun rememberBlueTaxiState(
    navHostController: NavHostController = rememberNavController(),
) = BlueTaxiState(navHostController)