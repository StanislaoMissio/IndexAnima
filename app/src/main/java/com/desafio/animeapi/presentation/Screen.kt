package com.desafio.animeapi.presentation

sealed class Screen(val route: String) {

    object AnimeListScreen: Screen("anime_list_screen")
    object AnimeDetailScreen: Screen("anime_detail_screen")

}
