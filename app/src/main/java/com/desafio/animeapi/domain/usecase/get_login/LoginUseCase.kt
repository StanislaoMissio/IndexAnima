package com.desafio.animeapi.domain.usecase.get_login

data class LoginUseCase(
    val doLoginUseCase: DoLoginUseCase,
    val doLoginWithGoogle: DoLoginWithGoogle,
    val doLogoutUseCase: DoLogoutUseCase
)