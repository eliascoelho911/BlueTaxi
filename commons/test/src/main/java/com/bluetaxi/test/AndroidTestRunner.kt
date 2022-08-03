package com.bluetaxi.test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class AndroidTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?,
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}