package com.desafio.animeapi.data.remote.dto

import com.desafio.animeapi.data.remote.Attributes
import com.desafio.animeapi.domain.model.Anime

data class AnimeDto(
    val attributes: Attributes,
    val id: String,
    val type: String
)

fun AnimeDto.toAnime(): Anime = Anime(attributes = attributes, id = id, type = type)