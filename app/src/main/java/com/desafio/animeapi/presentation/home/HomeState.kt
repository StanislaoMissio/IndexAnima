package com.desafio.animeapi.presentation.home

import com.desafio.animeapi.domain.model.Anime

data class HomeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val topAnime: List<Anime> = emptyList(),
    val animeForYou: List<Anime> = emptyList(),
    val recentlyAdded: List<Anime> = emptyList()
)