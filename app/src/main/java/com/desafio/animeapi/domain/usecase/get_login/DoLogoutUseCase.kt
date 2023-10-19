package com.desafio.animeapi.domain.usecase.get_login

import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.repository.Authenticator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DoLogoutUseCase(private val auth: Authenticator) {

    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        val result = try {
            auth.signOut()
            val isLoggedOut = auth.getSignInUser() == null
            Resource.Success(data = isLoggedOut)
        } catch (exception: Exception) {
            Resource.Error(message = exception.localizedMessage ?: "Ops, Algo deu errado!")
        }
        emit(result)
    }

}