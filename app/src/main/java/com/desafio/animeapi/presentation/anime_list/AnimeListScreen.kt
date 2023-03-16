package com.desafio.animeapi.presentation.anime_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.desafio.animeapi.presentation.Screen
import com.desafio.animeapi.presentation.anime_list.components.AnimeListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnimeListScreen(
    navController: NavController,
    viewModel: AnimeListViewModel = koinViewModel<AnimeListViewModel>()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.animes) { anime ->
                AnimeListItem(anime = anime, onItemClick = {
                    navController.navigate(Screen.AnimeDetailScreen.route + "/${anime.id}")
                })
            }
        }
        if (state.error.isNotBlank()) {
            Text(text = state.error)
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}