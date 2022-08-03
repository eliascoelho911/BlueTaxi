package com.bluetaxi.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluetaxi.designsystem.theme.BlueTaxiTheme

@Composable
fun ProgressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = CircleShape,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    loadingContent: @Composable () -> Unit = {
        CircularProgressIndicator(modifier = Modifier.size(24.dp),
            color = LocalContentColor.current, strokeWidth = 2.dp)
    },
    content: @Composable () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = if (isLoading) enabled else false,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding
    ) {
        Box(contentAlignment = Alignment.Center) {
            AnimatedVisibility(visible = isLoading, content = loadingContent)
            AnimatedVisibility(visible = !isLoading, content = content)
        }
    }
}

@Composable
private fun AnimatedVisibility(
    content: @Composable () -> Unit,
    visible: Boolean,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(AnimationDurationMillis)) + slideInVertically(tween(
            AnimationDurationMillis)),
        exit = fadeOut()
    ) {
        content()
    }
}

private const val AnimationDurationMillis = 500

@Composable
@Preview("Light - Loading")
private fun ProgressButtonLightLoading() {
    BlueTaxiTheme(useDarkTheme = false) {
        ProgressButton(onClick = {}, isLoading = true) {}
    }
}

@Composable
@Preview("Dark - Content -> Success")
private fun ProgressButtonDarkContentSuccess() {
    BlueTaxiTheme(useDarkTheme = false) {
        ProgressButton(onClick = {}, isLoading = false) { Text(text = "Submit") }
    }
}