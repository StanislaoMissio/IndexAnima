package com.desafio.animeapi.domain.usecase.get_login

import com.desafio.animeapi.common.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.IOException

class DoLoginUseCase(private val auth: FirebaseAuth) {
    operator fun invoke(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        val result = try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(data = result)
        } catch (exception: FirebaseAuthException) {
            Resource.Error(message = "Erro ao efetuar login, tente novamente")
        } catch (exception: IOException) {
            Resource.Error(message = "Algo deu errado, tente novamente mais tarde!")
        } catch (exception: Exception) {
            Resource.Error(message = "Algo deu errado, tente novamente mais tarde!")
        }
        emit(result)
    }
}