package com.desafio.animeapi.presentation.login

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val error: String = "",
    val login: Boolean = false,
    val isLoading: Boolean = false
)