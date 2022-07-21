package com.github.eliascoelho911.bluetaxi.commons

object EmailValidator {
    fun isEmail(value: String): Boolean =
        Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(value)
 }