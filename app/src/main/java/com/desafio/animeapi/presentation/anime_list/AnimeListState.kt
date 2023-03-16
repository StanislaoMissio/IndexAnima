package com.desafio.animeapi.presentation.anime_list

import com.desafio.animeapi.domain.model.Anime

data class AnimeListState(
    val isLoading: Boolean = false,
    val animes: List<Anime> = emptyList(),
    val error: String = ""
)
