package com.desafio.animeapi.presentation.anime_detail

import com.desafio.animeapi.domain.model.Anime

data class AnimeDetailState(
    val isLoading: Boolean = false,
    val animeDetail: Anime? = null,
    val error: String = ""
)