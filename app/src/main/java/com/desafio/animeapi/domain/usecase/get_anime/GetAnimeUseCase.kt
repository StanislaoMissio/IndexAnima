package com.desafio.animeapi.domain.usecase.get_anime

import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.data.remote.dto.toAnime
import com.desafio.animeapi.domain.model.Anime
import com.desafio.animeapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetAnimeUseCase(private val repository: Repository) {

    operator fun invoke(animeId: String): Flow<Resource<Anime>> = flow {
        try {
            emit(Resource.Loading())
            val anime = repository.getAnime(animeId).data[0].toAnime()
            emit(Resource.Success(anime))
        } catch (exception: HttpException) {
            emit(Resource.Error(exception.localizedMessage ?: "Ocorreu um erro inesperado"))
        } catch (exception: IOException) {
            emit(Resource.Error("Não foi possivel se conectar, verifique sua conexão com a internet"))
        }
    }
}