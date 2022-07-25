package com.github.eliascoelho911.bluetaxi.domain.di

import com.github.eliascoelho911.bluetaxi.domain.usecases.LoginUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val DomainModule = module {
    useCases()
}

private fun Module.useCases() {
    single { LoginUseCase() }
}
