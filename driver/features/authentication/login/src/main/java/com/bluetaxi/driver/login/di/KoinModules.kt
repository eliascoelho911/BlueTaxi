package com.bluetaxi.driver.login.di

import com.bluetaxi.authentication.domain.usecases.LoginUseCase
import com.bluetaxi.login.di.LoginModule
import com.bluetaxi.login.domain.repositories.LoginRepository
import com.bluetaxi.driver.login.data.LoginRepositoryMock
import com.bluetaxi.driver.login.domain.LoginUseCaseImpl
import org.koin.dsl.module

val DriverLoginModule = LoginModule.plus(module {
    single<LoginRepository> { LoginRepositoryMock() }
    single<LoginUseCase> { LoginUseCaseImpl(get()) }
})