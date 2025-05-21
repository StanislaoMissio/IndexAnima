package com.desafio.animeapi.presentation.register

data class RegisterState(
    val error: String = "",
    val isLoading: Boolean = false,
    val registered: Boolean = false
)