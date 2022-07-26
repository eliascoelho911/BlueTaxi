package com.github.eliascoelho911.bluetaxi.commons

import androidx.navigation.NavHostController

val NavHostController.popBackStackOrNull: (() -> Unit)?
    get() = if (backQueue.isNotEmpty()) {
        { popBackStack() }
    } else null