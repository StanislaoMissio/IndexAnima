package com.desafio.animeapi.domain.repository

import com.desafio.animeapi.data.remote.dto.AnimeDto
import com.desafio.animeapi.data.remote.Response

interface AnimeRepository {
    suspend fun getAnimes(): Response<AnimeDto>
    suspend fun getAnime(animeId: String): Response<AnimeDto>
    suspend fun getTopAnimeList(): Response<AnimeDto>
}