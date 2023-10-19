package com.desafio.animeapi.domain.usecase.do_initialize

import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.data.remote.User
import com.desafio.animeapi.domain.repository.Authenticator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DoInitializeApplication(private val auth: Authenticator) {

    operator fun invoke(): Flow<Resource<User?>> = flow {
        emit(Resource.Loading())
        val result = try {
            val user = auth.getSignInUser()?.run {
                User(
                    userId = uid,
                    username = displayName,
                    profilePictureUrl = photoUrl.toString()
                )
            }
            Resource.Success(user)
        } catch (exception: Exception) {
            Resource.Error(
                message = exception.localizedMessage
                    ?: "Ops, algo deu errado. Tente novamente mais tarde!"
            )
        }
        emit(result)
    }
}