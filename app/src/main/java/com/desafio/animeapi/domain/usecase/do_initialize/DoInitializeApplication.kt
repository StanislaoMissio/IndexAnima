package com.desafio.animeapi.domain.usecase.do_initialize

import com.desafio.animeapi.common.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class DoInitializeApplication {

    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        val result = try {
            val user = FirebaseAuth.getInstance().currentUser != null
            Resource.Success(data = user)
        } catch (exception: IOException) {
            Resource.Error(
                message = exception.localizedMessage ?: "Conection problem, try again later!"
            )
        } catch (exception: FirebaseAuthException) {
            Resource.Error(
                message = exception.localizedMessage ?: "Someting went wrong, try again later!"
            )
        }
        emit(result)
    }
}