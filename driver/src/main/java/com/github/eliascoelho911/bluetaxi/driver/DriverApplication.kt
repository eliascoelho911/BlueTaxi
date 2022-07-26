package com.github.eliascoelho911.bluetaxi.driver

import android.app.Application
import com.github.eliascoelho911.bluetaxi.domain.di.DomainModule
import com.github.eliascoelho911.bluetaxi.ui.login.LoginUiModule
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