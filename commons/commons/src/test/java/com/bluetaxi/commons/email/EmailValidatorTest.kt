package com.bluetaxi.commons.email

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {
    @Test
    fun givenValidEmail_whenCallIsEmail_shouldReturnTrue() {
        val validEmail = "elias@hotmail.com"

        assertTrue(EmailValidator.isEmail(validEmail))
    }

    @Test
    fun givenInvalidEmail_whenCallIsEmail_shouldReturnFalse() {
        val invalidEmail = "elias@hotmailcom"

        assertFalse(EmailValidator.isEmail(invalidEmail))
    }
}