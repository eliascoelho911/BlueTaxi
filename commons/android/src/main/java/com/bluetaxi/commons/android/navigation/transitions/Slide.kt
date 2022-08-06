package com.bluetaxi.commons.android.navigation.transitions

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun transitionSlideInHorizontal() = slideInHorizontally(animationSpec = tween(500)) { -it }

fun transitionSlideOutHorizontal() = slideOutHorizontally(animationSpec = tween(500)) { -it }