package com.desafio.animeapi.di

import com.desafio.animeapi.data.remote.dto.AnimeDto
import com.desafio.animeapi.data.remote.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("anime")
    suspend fun getAnimes(): Response<AnimeDto>

    @GET("anime?filter[id]={id}")
    suspend fun getAnime(@Path("id") animeId: String): Response<AnimeDto>

    @GET("anime?filter[id]={id}")
    suspend fun getTopAnimeList(@Path("id") animeId: String): Response<AnimeDto>

}