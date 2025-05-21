package com.desafio.animeapi.presentation.splash_screen

import com.desafio.animeapi.data.remote.User

data class SplashScreenState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)