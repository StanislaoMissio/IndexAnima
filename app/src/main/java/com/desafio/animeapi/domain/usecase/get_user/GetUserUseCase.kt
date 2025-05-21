package com.desafio.animeapi.domain.usecase.get_user

import com.desafio.animeapi.data.remote.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class GetUserUseCase {

    operator fun invoke(): Flow<User?> = callbackFlow {

    }
}