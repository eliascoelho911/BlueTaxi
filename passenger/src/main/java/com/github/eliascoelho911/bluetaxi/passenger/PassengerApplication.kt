package com.github.eliascoelho911.bluetaxi.passenger

import android.app.Application
import com.github.eliascoelho911.bluetaxi.domain.di.DomainModule
import com.github.eliascoelho911.bluetaxi.ui.login.LoginUiModule
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