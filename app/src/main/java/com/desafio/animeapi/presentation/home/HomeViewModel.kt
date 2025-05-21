package com.desafio.animeapi.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.usecase.get_animelist.GetTopAnimeListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    val getTopAnimeListUseCase: GetTopAnimeListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getTopAnime()
    }

    private fun getTopAnime() {
        getTopAnimeListUseCase().onEach {
            when (it) {
                is Resource.Loading -> _state.value = HomeState(isLoading = true)
                is Resource.Success -> _state.value = HomeState(topAnime = it.data ?: emptyList())
                is Resource.Error -> _state.value =
                    HomeState(error = it.message ?: "Tente novamente mais tarde.")
            }
        }.launchIn(viewModelScope)
    }
}