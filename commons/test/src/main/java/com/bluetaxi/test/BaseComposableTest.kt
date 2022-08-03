package com.bluetaxi.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule

abstract class BaseComposableTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    fun composableTest(
        block: ComposeContentTestRule.() -> Unit,
    ) {
        composeTestRule.block()
    }
}