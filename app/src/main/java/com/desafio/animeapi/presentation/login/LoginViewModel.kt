package com.desafio.animeapi.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.desafio.animeapi.common.Constants.LOGIN_LABEL
import com.desafio.animeapi.common.Constants.PASSWORD_LABEL
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.common.let2
import com.desafio.animeapi.domain.usecase.get_login.DoLoginUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(
    val doLoginUseCase: DoLoginUseCase
) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun doLogin() {
        doLoginUseCase(email.value, password.value).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = LoginState(isLoading = true)
                is Resource.Success -> _state.value =
                    LoginState(login = true)
                is Resource.Error -> _state.value =
                    LoginState(error = result.message ?: "An unexpected error occurred")
            }
        }.launchIn(viewModelScope)
    }

}