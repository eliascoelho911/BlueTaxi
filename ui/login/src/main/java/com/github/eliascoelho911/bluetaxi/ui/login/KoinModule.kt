package com.github.eliascoelho911.bluetaxi.ui.login

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val LoginUiModule = module {
    viewModel { LoginViewModel(get()) }
}