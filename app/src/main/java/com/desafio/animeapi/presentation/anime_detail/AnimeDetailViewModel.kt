package com.desafio.animeapi.presentation.anime_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.desafio.animeapi.common.Constants.ANIME_ID
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.model.Anime
import com.desafio.animeapi.domain.usecase.get_anime.GetAnimeUseCase
import kotlinx.coroutines.flow.onEach

class AnimeDetailViewModel(
    private val getAnimeUseCase: GetAnimeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _anime = MutableLiveData<Anime>()
    val anime: LiveData<Anime> = _anime

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        savedStateHandle.get<String>(ANIME_ID)?.let { getAnime(it) }
    }

    private fun getAnime(animeId: String) {
        getAnimeUseCase(animeId).onEach {
            when (it) {
                is Resource.Loading -> _loading.value = true
                is Resource.Success -> {
                    _anime.value = it.data?.let { data -> data[0] }
                    _loading.value = false
                }
                is Resource.Error -> {
                    _error.value = it.message!!
                    _loading.value = false
                }
            }
        }

    }

}