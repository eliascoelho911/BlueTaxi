package com.github.eliascoelho911.bluetaxi.ui.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.github.eliascoelho911.bluetaxi.designsystem.util.screenPadding

@Composable
fun WelcomeScreen(
    title: @Composable ColumnScope.() -> Unit,
    subtitle: @Composable ColumnScope.() -> Unit,
    onClickEnterWithEmail: () -> Unit,
    onClickSignUp: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(modifier = Modifier.screenPadding()) {
            val (bodyRef, bottomsRef) = createRefs()

            Column(
                modifier = Modifier.constrainAs(bodyRef) {
                    bottom.linkTo(bottomsRef.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
                    title()
                }
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.bodyLarge) {
                    subtitle()
                }
            }

            Column(
                modifier = Modifier.constrainAs(bottomsRef) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = onClickEnterWithEmail, modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(id = R.string.enter_with_email))
                }

                TextButton(onClick = onClickSignUp) {
                    Text(text = buildAnnotatedString {
                        append(stringResource(id = R.string.dont_have_account))
                        append(" ")

                        withStyle(style = SpanStyle(
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold
                        )) {
                            append(stringResource(id = R.string.registration))
                        }
                    })
                }
            }
        }
    }
}