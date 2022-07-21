package com.github.eliascoelho911.bluetaxi.auth.di

import com.github.eliascoelho911.bluetaxi.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val LoginModule = module {
    viewModels()
}

private fun Module.viewModels() {
    viewModel { LoginViewModel() }
}
