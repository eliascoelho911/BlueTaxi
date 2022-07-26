package com.github.eliascoelho911.bluetaxi.designsystem.util

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val ScreenPadding = 16.dp

fun Modifier.screenPadding() = this.then(
    Modifier.padding(
        horizontal = ScreenPadding,
        vertical = ScreenPadding
    ))

fun Modifier.verticalScreenPadding() = this.then(
    Modifier.padding(
        vertical = ScreenPadding
    ))

fun Modifier.horizontalScreenPadding() = this.then(
    Modifier.padding(
        horizontal = ScreenPadding,
    ))

