package com.github.eliascoelho911.bluetaxi.commons.test

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert

fun SemanticsNodeInteraction.assertHasError() {
    assert(hasError())
}

fun SemanticsNodeInteraction.assertHasNoError() {
    assert(!hasError())
}

private fun hasError() = hasKey(SemanticsProperties.Error)

private fun hasKey(semanticsPropertyKey: SemanticsPropertyKey<String>) =
    SemanticsMatcher.keyIsDefined(semanticsPropertyKey)