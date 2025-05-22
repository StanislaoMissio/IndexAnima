package com.desafio.animeapi.presentation.anime_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.model.Anime
import com.desafio.animeapi.domain.usecase.get_anime.GetAnimeUseCase
import kotlinx.coroutines.flow.onEach

class AnimeDetailViewModel(private val getAnimeUseCase: GetAnimeUseCase) : ViewModel() {

    private val _state = mutableStateOf(AnimeDetailState())
    val state: State<AnimeDetailState> = _state

//    fun getAnimeDetail(anime: String) {
//        getAnimeUseCase(anime).onEach { result ->
//            when (result) {
//                is Resource.Loading -> _state.value = AnimeDetailState(isLoading = true)
//                is Resource.Success -> _state.value = AnimeDetailState(animeDetail = result.data)
//                is Resource.Error -> _state.value = AnimeDetailState(
//                    error = result.message ?: "Something went wrong, try again later!"
//                )
//            }
//        }
//    }

}