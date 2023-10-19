package com.desafio.animeapi.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.usecase.get_login.LoginUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(
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

    fun doLoginWithGoogle() {
        loginUseCase.doLoginWithGoogle().onEach { result ->
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

    fun signOut() {
        loginUseCase.doLogoutUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = LoginState(isLoading = true)
                is Resource.Success -> _state.value = LoginState(login = result.data ?: true)
                is Resource.Error -> _state.value = LoginState(
                    error = result.message ?: "Algo deu errado, tente novamente mais tarde!"
                )
            }
        }.launchIn(viewModelScope)
    }

    fun cleanErrorState() {
        _state.value = LoginState(error = "")
    }
}