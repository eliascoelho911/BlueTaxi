package com.github.eliascoelho911.bluetaxi.designsystem.components

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.github.eliascoelho911.bluetaxi.designsystem.R
import com.github.eliascoelho911.bluetaxi.designsystem.theme.BlueTaxiTheme

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    passwordVisibleInitially: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.extraSmall,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
) {
    var passIsVisible by rememberSaveable { mutableStateOf(passwordVisibleInitially) }

    TextField(value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        trailingIcon = {
            val icon = if (passIsVisible) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility
            @StringRes val contentDescription =
                if (passIsVisible) R.string.cd_hide_password else R.string.cd_show_password

            IconButton(onClick = { passIsVisible = !passIsVisible }) {
                Icon(icon, stringResource(id = contentDescription))
            }
        },
        isError = isError,
        visualTransformation = if (passIsVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        singleLine = true,
        maxLines = 1
    )
}

@Preview
@Composable
private fun WithPasswordVisibleInitiallyTrue() {
    BlueTaxiTheme {
        PasswordTextField(value = "senha", onValueChange = {}, passwordVisibleInitially = true)
    }
}

@Preview
@Composable
private fun WithPasswordVisibleInitiallyFalse() {
    BlueTaxiTheme {
        PasswordTextField(value = "senha", onValueChange = {}, passwordVisibleInitially = false)
    }
}