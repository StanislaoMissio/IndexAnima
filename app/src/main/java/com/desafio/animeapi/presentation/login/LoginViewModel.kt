package com.desafio.animeapi.presentation.login

import android.content.Intent
import android.content.IntentSender
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.animeapi.common.Constants
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.usecase.get_login.LoginUseCase
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun doLoginWithEmailAndPassword() {
        loginUseCase.doLoginUseCase(email.value, password.value).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = LoginState(isLoading = true)
                is Resource.Success -> _state.value =
                    LoginState(login = true)

                is Resource.Error -> _state.value =
                    LoginState(
                        error = result.message ?: "Algo deu errado, tente novamente mais tarde!"
                    )
            }
        }.launchIn(viewModelScope)
    }

    fun doLoginWithGoogle(intent: Intent) {
        loginUseCase.doLoginWithGoogle(intent).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = LoginState(isLoading = true)
                is Resource.Success -> _state.value =
                    LoginState(login = true)

                is Resource.Error -> _state.value =
                    LoginState(
                        error = result.message ?: "Algo deu errado, tente novamente mais tarde!"
                    )
            }
        }.launchIn(viewModelScope)
    }

    fun cleanErrorState() {
        _state.value = LoginState(error = "")
    }
}