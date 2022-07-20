package com.github.eliascoelho911.bluetaxi.designsystem.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProgressButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    state: ProgressButtonState = ProgressButtonState.CONTENT,
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
    successContent: @Composable () -> Unit = {},
    failureContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Button(
        onClick = {
            if (state == ProgressButtonState.CONTENT) onClick()
        },
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding
    ) {
        val transition = updateTransition(targetState = state, label = "loadableButton")

        Box(contentAlignment = Alignment.Center) {
            transition.ShowContentOnState(content = loadingContent,
                state = ProgressButtonState.LOADING)
            transition.ShowContentOnState(content = successContent,
                state = ProgressButtonState.SUCCESS)
            transition.ShowContentOnState(content = failureContent,
                state = ProgressButtonState.FAILURE)
            transition.ShowContentOnState(content = content,
                state = ProgressButtonState.CONTENT)
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun Transition<ProgressButtonState>.ShowContentOnState(
    content: @Composable () -> Unit,
    state: ProgressButtonState,
) {
    AnimatedVisibility(
        visible = { it == state },
        enter = fadeIn(tween(AnimationDurationMillis)) + slideInVertically(tween(
            AnimationDurationMillis)),
        exit = fadeOut()
    ) {
        content()
    }
}

enum class ProgressButtonState {
    LOADING, SUCCESS, FAILURE, CONTENT
}

private const val AnimationDurationMillis = 500

@Composable
@Preview("Light - Loading")
private fun ProgressButtonLightLoading() {
    BlueTaxiTheme(useDarkTheme = false) {
        ProgressButton(onClick = { }, state = ProgressButtonState.LOADING) {}
    }
}

@Composable
@Preview("Light - Content -> Success")
private fun ProgressButtonLightContentSuccess() {
    BlueTaxiTheme(useDarkTheme = false) {
        StateToStatePreview(initialState = ProgressButtonState.CONTENT,
            targetState = ProgressButtonState.SUCCESS)
    }
}

@Composable
@Preview("Light - Content -> Failed")
private fun ProgressButtonLightContentFailed() {
    BlueTaxiTheme(useDarkTheme = false) {
        StateToStatePreview(initialState = ProgressButtonState.CONTENT,
            targetState = ProgressButtonState.FAILURE)
    }
}

@Composable
@Preview("Dark - Content -> Success")
private fun ProgressButtonDarkContentSuccess() {
    BlueTaxiTheme(useDarkTheme = false) {
        StateToStatePreview(initialState = ProgressButtonState.CONTENT,
            targetState = ProgressButtonState.SUCCESS)
    }
}

@Composable
@Preview("Dark - Content -> Failed")
private fun ProgressButtonDarkContentFailed() {
    BlueTaxiTheme(useDarkTheme = false) {
        StateToStatePreview(initialState = ProgressButtonState.CONTENT,
            targetState = ProgressButtonState.FAILURE)
    }
}

@Composable
private fun StateToStatePreview(
    initialState: ProgressButtonState,
    targetState: ProgressButtonState,
) {
    var state by remember { mutableStateOf(initialState) }
    val coroutineScope = rememberCoroutineScope()

    ProgressButton(
        onClick = {
            coroutineScope.launch {
                state = ProgressButtonState.LOADING
                delay(1000)
                state = targetState
            }
        },
        state = state,
        successContent = { Icon(imageVector = Icons.Rounded.Done, contentDescription = null) },
        failureContent = { Icon(imageVector = Icons.Rounded.Close, contentDescription = null) },
        content = {
            Text(text = "Button")
        })
}