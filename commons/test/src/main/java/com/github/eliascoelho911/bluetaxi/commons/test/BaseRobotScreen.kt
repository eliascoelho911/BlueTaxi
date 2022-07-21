package com.github.eliascoelho911.bluetaxi.commons.test

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

open class BaseRobotScreen(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>,
) {
    protected fun withRule(block: ComposeContentTestRule.() -> Unit) {
        composeTestRule.block()
    }

    protected fun getString(@StringRes resourceId: Int) =
        composeTestRule.activity.getString(resourceId)
}