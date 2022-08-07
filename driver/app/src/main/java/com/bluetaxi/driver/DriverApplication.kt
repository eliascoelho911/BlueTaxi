package com.bluetaxi.driver

import android.app.Application
import com.bluetaxi.passenger.login.di.DriverLoginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DriverApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DriverApplication)
            modules(DriverLoginModule)
        }
    }
}