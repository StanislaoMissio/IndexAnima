package com.desafio.animeapi.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafio.animeapi.common.Resource
import com.desafio.animeapi.common.let2
import com.desafio.animeapi.domain.usecase.do_register.DoRegisterUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegisterViewModel(val doRegisterUseCase: DoRegisterUseCase) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")

    private val _state = MutableLiveData<RegisterState>()
    val state: LiveData<RegisterState>
        get() = _state

    fun doRegister() {
        doRegisterUseCase(email.value, password.value).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = RegisterState(isLoading = true)
                is Resource.Error -> _state.value =
                    RegisterState(error = "An unexpected error occured")
                is Resource.Success -> _state.value = RegisterState(user = result.data?.user)
            }
        }.launchIn(viewModelScope)
    }

}