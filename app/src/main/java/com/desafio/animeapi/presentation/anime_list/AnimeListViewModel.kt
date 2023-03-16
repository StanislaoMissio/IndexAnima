package com.desafio.animeapi.presentation.anime_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.usecase.get_animes.GetAnimesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AnimeListViewModel(private val getAnimeUseCase: GetAnimesUseCase) : ViewModel() {

    private val _state = mutableStateOf(AnimeListState())
    val state: State<AnimeListState> = _state

    init {
        getAnimes()
    }

    private fun getAnimes() {
        getAnimeUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = AnimeListState(isLoading = true)
                is Resource.Success ->
                    _state.value = AnimeListState(animes = result.data ?: emptyList())
                is Resource.Error ->
                    _state.value = AnimeListState(error = "An unexpected error occured")
            }
        }.launchIn(viewModelScope)
    }

}