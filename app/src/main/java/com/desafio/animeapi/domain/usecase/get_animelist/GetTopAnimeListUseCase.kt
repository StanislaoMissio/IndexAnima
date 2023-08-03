package com.desafio.animeapi.domain.usecase.get_animelist

import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.model.Anime
import com.desafio.animeapi.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTopAnimeListUseCase(val repository: Repository) {

    operator fun invoke() : Flow<Resource<List<Anime>>> = flow {
        try{

        } catch (httpException: HttpException) {

        } catch (exception: IOException) {

        }
    }

}