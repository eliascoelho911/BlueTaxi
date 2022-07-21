package com.github.eliascoelho911.bluetaxi.core.test

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        runCatching {
            startKoin {
                androidContext(this@TestApplication)
                allowOverride(true)
            }
        }
    }
}