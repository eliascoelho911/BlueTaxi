package com.bluetaxi.core.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class StateViewModel<State : UiState>(initialState: State) : ViewModel() {
    var uiState by mutableStateOf(initialState)
        private set

    protected fun setUiState(state: (State) -> State) {
        uiState = state(uiState)
    }
}