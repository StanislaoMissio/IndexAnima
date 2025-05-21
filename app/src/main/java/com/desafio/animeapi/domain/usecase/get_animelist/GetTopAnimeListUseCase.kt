package com.desafio.animeapi.domain.usecase.get_animelist

import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.data.remote.dto.toAnime
import com.desafio.animeapi.domain.model.Anime
import com.desafio.animeapi.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTopAnimeListUseCase(private val animeRepository: AnimeRepository) {

    operator fun invoke(): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())
        val result = try {
            val topAnime = animeRepository.getTopAnimeList().data.map { it.toAnime() }
            Resource.Success(data = topAnime)
        } catch (httpException: HttpException) {
            Resource.Error(
                httpException.message ?: "Algo deu errado, tente novamente mais tarde!"
            )
        } catch (exception: IOException) {
            Resource.Error(
                exception.message ?: "Algo deu errado, tente novamente mais tarde!"
            )
        }
        emit(result)
    }

}