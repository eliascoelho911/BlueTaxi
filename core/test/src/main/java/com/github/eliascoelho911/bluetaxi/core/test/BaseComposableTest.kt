package com.github.eliascoelho911.bluetaxi.core.test

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

abstract class BaseComposableTest {
    @get:Rule
    protected val composeTestRule = createComposeRule()
}