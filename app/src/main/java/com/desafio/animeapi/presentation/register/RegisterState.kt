package com.desafio.animeapi.presentation.register

import com.google.firebase.auth.FirebaseUser

data class RegisterState(
    val error: String = "",
    val isLoading: Boolean = false,
    val user: FirebaseUser? = null
)