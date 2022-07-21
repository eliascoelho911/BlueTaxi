package com.github.eliascoelho911.bluetaxi.core.test

import androidx.compose.ui.test.junit4.ComposeContentTestRule

open class ComposeTestRobot(private val composeTestRule: ComposeContentTestRule) {
    protected fun withRule(block: ComposeContentTestRule.() -> Unit) {
        composeTestRule.block()
    }
}