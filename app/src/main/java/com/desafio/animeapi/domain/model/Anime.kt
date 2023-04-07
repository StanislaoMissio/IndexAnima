package com.desafio.animeapi.domain.model

import com.desafio.animeapi.data.remote.Attributes

data class Anime(
    val attributes: Attributes,
    val id: String,
    val type: String
)