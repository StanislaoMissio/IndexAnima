package com.desafio.animeapi.data.remote

import com.desafio.animeapi.domain.model.Anime

data class User(
    val userId: String? = "",
    val username: String? = "",
    val profilePictureUrl: String? = "",
    val favoriteList: List<Anime> = emptyList(),
    val lists: List<List<Anime>> = emptyList()
)