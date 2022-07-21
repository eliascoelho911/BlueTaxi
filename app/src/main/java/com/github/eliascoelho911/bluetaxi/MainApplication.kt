package com.github.eliascoelho911.bluetaxi

import android.app.Application
import com.github.eliascoelho911.bluetaxi.auth.di.LoginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(LoginModule)
        }
    }
}