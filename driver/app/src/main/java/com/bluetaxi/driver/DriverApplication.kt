package com.bluetaxi.driver

import android.app.Application
import com.github.eliascoelho911.bluetaxi.domain.di.DomainModule
import com.bluetaxi.login.di.LoginUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DriverApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DriverApplication)
            modules(DomainModule, LoginUiModule)
        }
    }
}