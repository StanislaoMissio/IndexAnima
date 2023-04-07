package com.desafio.animeapi.data.repository

import com.desafio.animeapi.data.remote.dto.AnimeDto
import com.desafio.animeapi.data.remote.Response
import com.desafio.animeapi.di.Api
import com.desafio.animeapi.domain.repository.Repository

class RepositoryImpl(private val api: Api) : Repository {

    override suspend fun getAnimes(): Response<AnimeDto> = api.getAnimes()
    override suspend fun getAnime(animeId: String): Response<AnimeDto> = api.getAnime(animeId)

}