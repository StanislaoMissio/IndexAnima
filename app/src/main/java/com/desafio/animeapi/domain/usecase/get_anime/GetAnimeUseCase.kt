package com.desafio.animeapi.domain.usecase.get_anime

import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.data.remote.dto.toAnime
import com.desafio.animeapi.domain.model.Anime
import com.desafio.animeapi.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAnimeUseCase(private val animeRepository: AnimeRepository) {

    operator fun invoke(animeId: String): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())
        val result = try {
            val anime = animeRepository.getAnime(animeId).data.map { it.toAnime() }
            Resource.Success(anime)
        } catch (exception: HttpException) {
            Resource.Error(exception.localizedMessage ?: "Ocorreu um erro inesperado")
        } catch (exception: IOException) {
            Resource.Error("Não foi possivel se conectar, verifique sua conexão com a internet")
        }
        emit(result)
    }
}