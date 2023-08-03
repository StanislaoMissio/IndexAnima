package com.desafio.animeapi.presentation.home

import androidx.lifecycle.ViewModel
import com.desafio.animeapi.domain.usecase.get_animelist.GetTopAnimeListUseCase

class HomeViewModel(
    val getTopAnimeListUseCase: GetTopAnimeListUseCase
) : ViewModel() {


}