package com.desafio.animeapi.presentation.login

data class LoginState(
    val error: String = "",
    val login: Boolean = false,
    val isLoading: Boolean = false
)