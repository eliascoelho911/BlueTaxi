package com.github.eliascoelho911.bluetaxi.auth.di

import com.github.eliascoelho911.bluetaxi.auth.domain.usecases.LoginUseCase
import com.github.eliascoelho911.bluetaxi.auth.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val LoginModule = module {
    viewModels()
    useCases()
}

private fun Module.viewModels() {
    viewModel { LoginViewModel(get()) }
}

private fun Module.useCases() {
    single { LoginUseCase() }
}
