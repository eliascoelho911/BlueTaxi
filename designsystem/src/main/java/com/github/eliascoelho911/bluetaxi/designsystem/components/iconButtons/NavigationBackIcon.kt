package com.github.eliascoelho911.bluetaxi.designsystem.components.iconButtons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.eliascoelho911.bluetaxi.designsystem.R

@Composable
fun NavigationBackIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = R.string.cd_back),
) {
    IconButton(onClick, modifier) {
        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription)
    }
}
