package com.bluetaxi.passenger

import android.app.Application
import com.bluetaxi.passenger.login.di.PassengerLoginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PassengerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PassengerApplication)
            modules(PassengerLoginModule)
        }
    }
}