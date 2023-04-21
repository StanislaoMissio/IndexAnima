package com.desafio.animeapi.presentation.splash_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.usecase.do_initialize.DoInitializeApplication
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SplashScreenViewModel(
    val doInitializeApplication: DoInitializeApplication
) : ViewModel() {

    private val _state = mutableStateOf(SplashScreenState())
    val state: State<SplashScreenState> = _state

    init {
        shouldAppInitialize()
    }

    private fun shouldAppInitialize() {
        doInitializeApplication().onEach { result ->
            when (result) {
                is Resource.Success -> _state.value = SplashScreenState(isLogged = true)
                is Resource.Error -> _state.value =
                    SplashScreenState(error = result.message ?: "Something went wrong")
                is Resource.Loading -> _state.value = SplashScreenState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

}