package com.desafio.animeapi.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.domain.usecase.do_register.DoRegisterUseCase
import com.desafio.animeapi.presentation.login.LoginState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegisterViewModel(private val doRegisterUseCase: DoRegisterUseCase) : ViewModel() {

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val repeatPassword = mutableStateOf("")
    val acceptedTerms = mutableStateOf(false)

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState>
        get() = _state

    fun doRegister() {
        if ((password.value == repeatPassword.value) && acceptedTerms.value) {
            doRegisterUseCase(email.value, password.value).onEach { result ->
                when (result) {
                    is Resource.Loading -> _state.value = RegisterState(isLoading = true)
                    is Resource.Error -> _state.value =
                        RegisterState(error = "An unexpected error occured")
                    is Resource.Success -> _state.value =
                        RegisterState(registered = result.data?.user != null)
                }
            }.launchIn(viewModelScope)
        } else {
            RegisterState(error = "")
        }
    }

    fun cleanErrorState() {
        _state.value = RegisterState(error = "")
    }

}