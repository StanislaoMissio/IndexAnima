package com.desafio.animeapi.domain.usecase.get_login

import android.content.Intent
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.data.remote.User
import com.desafio.animeapi.domain.repository.Authenticator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DoLoginWithGoogle(private val auth: Authenticator) {

    operator fun invoke(intent: Intent): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        val result = try {
            val user = auth.authenticateWithGoogleAuth(intent).run {
                User(
                    username = data?.username,
                    profilePictureUrl = data?.profilePictureUrl,
                    userId = data?.userId
                )
            }
            Resource.Success(user)
        } catch (exception: Exception) {
            Resource.Error(
                message = exception.localizedMessage
                    ?: "Ops algo deu errado no login, tente novamente mais tarde!"
            )
        }
        emit(result)
    }
}