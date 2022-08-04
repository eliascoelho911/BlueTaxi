package com.bluetaxi.commons.android.compose

import androidx.navigation.NavHostController

val NavHostController.hasBackQueue : Boolean get() = backQueue.isNotEmpty()