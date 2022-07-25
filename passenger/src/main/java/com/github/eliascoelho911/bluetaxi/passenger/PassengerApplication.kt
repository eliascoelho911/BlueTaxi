package com.github.eliascoelho911.bluetaxi.passenger

import android.app.Application
import com.github.eliascoelho911.bluetaxi.commons.ui.login.LoginUiModule
import com.github.eliascoelho911.bluetaxi.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PassengerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PassengerApplication)
            modules(DomainModule, LoginUiModule)
        }
    }
}