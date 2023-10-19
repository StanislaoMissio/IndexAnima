package com.desafio.animeapi.domain.usecase.get_login

import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.data.remote.User
import com.desafio.animeapi.domain.repository.Authenticator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DoLoginWithGoogle(private val auth: Authenticator) {

    operator fun invoke(): Flow<Resource<User?>> = flow {

    }
}