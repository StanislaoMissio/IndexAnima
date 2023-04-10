package com.desafio.animeapi.domain.usecase.do_initialize

import com.desafio.animeapi.common.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class DoInitializeApplication {

    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val user = FirebaseAuth.getInstance().currentUser != null
            emit(Resource.Success(data = user))
        } catch (exception: IOException) {
            emit(
                Resource.Error(
                    message = exception.localizedMessage ?: "Conection problem, try again later!"
                )
            )
        } catch (exception: FirebaseAuthException) {
            emit(
                Resource.Error(
                    message = exception.localizedMessage ?: "Someting went wrong, try again later!"
                )
            )
        }
    }
}