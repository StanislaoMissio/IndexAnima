package com.desafio.animeapi.presentation.splash_screen

data class SplashScreenState(
    val isLoading: Boolean = false,
    val isLogged: Boolean = false,
    val error: String = ""
)